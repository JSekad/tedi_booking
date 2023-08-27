import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, NavigationExtras } from '@angular/router';
import { PageEvent } from "@angular/material/paginator";
import { FormControl } from '@angular/forms';
import { Observable, startWith } from 'rxjs';
import { map } from 'rxjs';

import { SnackBarService } from 'src/app/services/snackBar.service';
import { SearchService } from '../../services/search.service';
import { CityService } from '../../services/city.service';
import { RoomTypeService } from '../../services/room-type.service';

import { Room } from './../../model/room.model';
import { RoomType } from './../../model/room-type.model';
import { City } from './../../model/city.model';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  startDate: Date = new Date();
  endDate: Date = new Date();
  today: Date;
  numPersons: number = 1;
  filterRoomType: RoomType | null | undefined;
  filterMaxCost: number = 0;
  cityForm = new FormControl('');
  pageIndex: number = 0;

  hasTV: boolean = false;
  hasWifi: boolean = false;
  hasAircondition: boolean = false;
  hasPrivateBathroom: boolean = false;
  hasJacuzzi: boolean = false;
  hasParking: boolean = false;
  hasKitchen: boolean = false;
  petsAllowed: boolean = false;
  smokingAllowed: boolean = false;
  partyAllowed: boolean = false;


  rooms: Room[] = [];
  filtredRooms: Room[] = [];
  cities : City[] = [];
  filtredCities: Observable<City[]> = new Observable<City[]>;
  roomTypes: RoomType[] = [];
  events: string[] = [];

  constructor( private searchService: SearchService, private message: SnackBarService,
               private router: Router, private cityService: CityService, private roomTypeService : RoomTypeService  ){
    this.today = new Date();
    this.today.setHours(0, 0, 0, 0);
    this.startDate = new Date();
    this.endDate = new Date();
  }

  ngOnInit(){
    this.cityService.getAllCities().subscribe({
      next: (response: City[]) => { this.cities = response; },

      complete: () => {
        this.filtredCities = this.cityForm.valueChanges.pipe(
          startWith(''),
          map(city => (city ? this.filterCities(city) : this.cities.slice()))
        )
      },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });

    this.roomTypeService.getAllRoomTypes().subscribe({
      next: (response) => { this.roomTypes = response; },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });
  }

  applyFilters(){
    this.filtredRooms = [];
    this.rooms.forEach(room => this.filtredRooms.push(room));

    if(this.filterMaxCost > 0)
      this.filtredRooms = this.filtredRooms.filter(room => {return room.basePricePerNight <= this.filterMaxCost; });

    if(this.filterRoomType != null)
      this.filtredRooms = this.filtredRooms.filter(room => {return room.type.id === this.filterRoomType?.id; });

    if(this.hasTV)
      this.filtredRooms = this.filtredRooms.filter(room => {return room.hasTV;} );

    if(this.hasWifi)
      this.filtredRooms = this.filtredRooms.filter(room => {return room.hasWifi;} );

    if(this.hasAircondition)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.hasAirCondition;} );

    if(this.hasPrivateBathroom)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.hasPrivateBathroom;} );

    if(this.hasJacuzzi)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.hasJacuzzi;} );

    if(this.hasParking)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.hasParking;} );

    if(this.hasKitchen)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.hasKitchen;} );

    if(this.petsAllowed)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.petsAllowed;} );

    if(this.smokingAllowed)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.smokingAllowed;} );

    if(this.partyAllowed)
      this.filtredRooms = this.filtredRooms.filter((room) => { return room.partyAllowed;} );
  }

  clearFilters(){
    this.filtredRooms = [];
    this.rooms.forEach(room => this.filtredRooms.push(room));
    this.filterRoomType = null;
    this.filterMaxCost = 0;
  }

  addMore(){
    this.filtredRooms.forEach(room => this.filtredRooms.push(room));
  }

  searchRooms(){

     if(this.searchInputValidation())
       return;

     this.searchService.getRooms(this.cityForm.value, this.formatDate(this.startDate), this.formatDate(this.endDate), this.numPersons).subscribe({

      next: ( response: Room[]) => {
        this.rooms = response;
        for(let i = 0; i < this.rooms.length; i++)
          if(this.rooms[i].defaultRoomImage != null)
            this.rooms[i].defaultRoomImage.image = 'data:image/jpeg;base64,' + this.rooms[i].defaultRoomImage.image;

        this.filtredRooms = [];
        this.rooms.forEach(room => this.filtredRooms.push(room));
      },

      complete: () => {
        if(this.rooms.length == 0)
          this.message.warn("Δεν βρέθηκαν διαθέσιμα δωμάτια με τα κριτήρια που δώσατε");
        else
          this.message.info("Βρέθηκαν διαθέσιμα δωμάτια!");
      },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
     });
  };

    private filterCities(value: string): City[]{
    return this.cities.filter(city => city.name.toLowerCase().includes(value.toLowerCase()));
  }

  reserveRoom(selectedRoomIndex: number){

    const navigationExtras: NavigationExtras = {
      state: {
        room: this.filtredRooms[selectedRoomIndex],
        numOfPersons: this.numPersons,
        startDate: this.formatDate(this.startDate),
        endDate:  this.formatDate(this.endDate)
      }
    };

    this.router.navigate(['/reservation'], navigationExtras);
  }

  private searchInputValidation(){

    if(this.cityForm.value === null || this.cityForm.value.length === 0){
      this.message.warn('Εισάγετε τοποθεσία');
      return true;
    }

    if(this.startDate === null || this.endDate === null){
      this.message.warn('Εισάγετε ημερομηνία από και εως');
      return true;
    }

    if(this.startDate.toISOString() == this.endDate.toISOString()){
      this.message.warn('Η διαμονή πρέπει να διαρκεί τουλάχιστον μια μέρα');
      return true;
    }

    if(this.startDate > this.endDate){
      this.message.warn('Η ημερομηνία έναρξης πρέπει να είναι μεταγενέστερη της ημερομηνίας λήξης');
      return true;
    }

    if(this.startDate < this.today){
        this.message.warn('Η ημερομηνία έναρξης της διαμονής, πρέπει να είναι μεγαλύτερη ή ίση της σημερινής.');
        return true;
    }

    if(this.numPersons < 1){
      this.message.warn('Εισάγετε αριθμό ατόμων.');
      return true;
    }

    return false;
  }

  public addEvent(type: string, event: MatDatepickerInputEvent<Date>){
    this.events.push(`${type}: ${event.value}`);
    let now = new Date();

    if(this.startDate != null){
      this.startDate.setHours(now.getHours());
      this.startDate.setMinutes(now.getMinutes());
      this.startDate.setSeconds(now.getSeconds());
    }

    if(this.endDate != null){
      this.endDate.setHours(now.getHours());
      this.endDate.setMinutes(now.getMinutes());
      this.endDate.setSeconds(now.getSeconds());
    }
  }

  changePageEvent(e: PageEvent){
    this.pageIndex = e.pageIndex;
  }

  private formatDate(date: Date): string{
    var splitDate = date.toLocaleDateString().split('/');
    return splitDate[2] + '-' + (Number(splitDate[0]) < 10 ? '0' + splitDate[0] : splitDate[0]) + '-' + (Number(splitDate[1]) < 10 ? '0' + splitDate[1] : splitDate[1])
  }

}
