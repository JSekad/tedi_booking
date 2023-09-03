import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Observable, startWith, map } from 'rxjs';
import { FormControl } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

import { City } from '../../model/city.model';
import { Room } from '../../model/room.model';
import { RoomImageDefault } from '../../model/roomImageDefault.model';
import { Property } from '../../model/property.model';
import { RoomType } from '../../model/room-type.model';
import { User } from '../../model/user.model';

import { AuthService } from 'src/app/services/auth-service.service';
import { SnackBarService } from 'src/app/services/snackBar.service';
import { CityService } from 'src/app/services/city.service';
import { RoomTypeService } from 'src/app/services/room-type.service';

@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css']
})
export class HostComponent {

  user: User | null;  
  address: string | null = null;
  addressNumber: string | null = null;
  description: string | null = null;
  accessInformation: string | null = null;
  cityForm = new FormControl('');
  cities : City[] = [];
  filtredCities: Observable<City[]> = new Observable<City[]>;
  roomType: RoomType | null = null;
  roomTypes: RoomType[] = [];
  filterRoomType: RoomType | null | undefined;
  longitude: number | null = null;
  latitude: number | null = null;

	basePricePerNight: number | null = null;
	defaultRoomImage: any = null;
  imagesUrls: any[] = [];
  images: any[] = [];
  numOfDoubleBeds: number = 0;
	numOfSingleBeds:number = 0;
	numOfBedrooms: number = 0;
	numOfBathrooms: number = 0;
	areaSize: number = 0;
	capacity: number = 0;
	numOfReviews: number = 0;
	averageReviews: number = 0;
	minRentDays: number = 1;
	hasTV: boolean = false;
	hasPrivateBathroom: boolean = false;
	hasAirCondition: boolean = false;
	hasWifi: boolean = false;
	hasKitchen: boolean = false;
	hasJacuzzi: boolean = false;
	petsAllowed: boolean = false;
	hasParking: boolean = false;
	hasElevator: boolean = false;
	smokingAllowed: boolean = false;
	partyAllowed: boolean = false;

  today: Date = new Date();
  startDate: Date = new Date();
  endDate: Date = new Date();
  events: string[] = [];

  constructor(private message: SnackBarService, private cityService: CityService, private changeDetectorRef: ChangeDetectorRef,
              private roomTypeService: RoomTypeService, private authService: AuthService){
    this.user = authService.getLoggedInUser(); 
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

  oncoordinatesSelected(event: any){
    this.longitude = event[0];
    this.latitude = event[1];
  }


  private filterCities(value: string): City[]{
    return this.cities.filter(city => city.name.toLowerCase().includes(value.toLowerCase()));
  }

  onAddDefaultImage(event: any){
    let file = event.target.files[0];
    var fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    this.imagesUrls.push(file);
    this.changeDetectorRef.detectChanges();
    fileReader.onload = (event) => {
      const url = (<FileReader>event.target).result as string;
      this.defaultRoomImage = url;
      this.changeDetectorRef.detectChanges();
    }
  }

  onAddImage(event: any){
    for(const file of event.target.files){
      var fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      this.imagesUrls.push(file);
      this.changeDetectorRef.detectChanges();
      fileReader.onload = (event) => {
        const url = (<FileReader>event.target).result as string;
        this.images.push(url);
        this.changeDetectorRef.detectChanges();
      }
    }
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

  private formatDate(date: Date): string{
    var splitDate = date.toLocaleDateString().split('/');
    // return splitDate[2] + '-' + (splitDate[1].length === 1 ? '0' + splitDate[1] : splitDate[1]) + '-' + (splitDate[0].length === 1 ? '0' + splitDate[0] : splitDate[0]);
    return splitDate[2] + '-' + (splitDate[0].length === 1 ? '0' + splitDate[0] : splitDate[0]) + '-' + (splitDate[1].length === 1 ? '0' + splitDate[1] : splitDate[1]);
  }

}

// class RoomClass implements Room{
// 	id: number | null = null;
// 	property: Property | null = null;
// 	type: RoomType | null = null;
// 	basePricePerNight: number | null = null;
// 	description: string | null = null;
// 	defaultRoomImage: RoomImageDefault | null = null;
// 	numOfDoubleBeds: number | null = null;
// 	numOfSingleBeds:number | null = null;
// 	numOfBedrooms: number | null = null;
// 	numOfBathrooms: number | null = null;
// 	minRentDays: number | null = null;
// 	hasTV: boolean | null = null;
// 	hasPrivateBathroom: boolean | null = null;
// 	hasAirCondition: boolean | null = null;
// 	hasWifi: boolean | null = null;
// 	hasKitchen: boolean | null = null;
// 	hasJacuzzi: boolean | null = null;
// 	areaSize: number | null = null;
// 	capacity: number | null = null;
// 	petsAllowed: boolean | null = null;
// 	hasParking: boolean | null = null;
// 	hasElevator: boolean | null = null;
// 	smokingAllowed: boolean | null = null;
// 	partyAllowed: boolean | null = null;
// 	numOfReviews: number | null = null;
// 	averageReviews: number | null = null;
// }