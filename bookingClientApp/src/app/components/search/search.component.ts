import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, NavigationExtras } from '@angular/router';
import { FormControl } from '@angular/forms';
import { Observable, startWith } from 'rxjs';
import { map } from 'rxjs';

import { SnackBarService } from 'src/app/services/snackBar.service';
import { SearchService } from '../../services/search.service';
import { CityService } from '../../services/city.service';

import { Room } from './../../model/room.model';
import { City } from './../../model/city.model';

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
  rooms: Room[] = [];
  defaultRoomImages: any[] = [];
  cities : City[] = [];
  filtredCities: Observable<City[]> = new Observable<City[]>;
  cityForm = new FormControl('');

  constructor( private searchService: SearchService, private message: SnackBarService,
               private router: Router, private cityService: CityService ){
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
  }

  searchRooms(){

     if(this.searchInputValidation())
       return;

     this.searchService.getRooms(this.cityForm.value, this.startDate.toISOString().split('T')[0], this.endDate.toISOString().split('T')[0], this.numPersons).subscribe({

      next: ( response: Room[]) => { this.rooms = response; },

      complete: () => {
        for(let i = 0; i < this.rooms.length; i++)
          this.defaultRoomImages[i]= 'data:image/jpeg;base64,' + this.rooms[i].defaultRoomImage.image;

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
        room: this.rooms[selectedRoomIndex], 
        defaultRoomImage: this.defaultRoomImages[selectedRoomIndex],
        numOfPersons: this.numPersons,
        startDate: this.startDate,
        endDate: this.endDate
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

}
