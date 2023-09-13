import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { PageEvent } from "@angular/material/paginator";
import { Observable, startWith, map } from 'rxjs';
import { FormControl, Validators } from '@angular/forms';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

import { Availability } from 'src/app/model/availability.model';
import { City } from '../../model/city.model';
import { Room } from '../../model/room.model';
import { RoomImageDefault } from '../../model/roomImageDefault.model';
import { Property } from '../../model/property.model';
import { RoomImage } from '../../model/room-image.model';
import { RoomType } from '../../model/room-type.model';
import { User } from '../../model/user.model';

import { AuthService } from 'src/app/services/auth-service.service';
import { AvailabilityService } from 'src/app/services/availability.service';
import { SnackBarService } from 'src/app/services/snackBar.service';
import { CityService } from 'src/app/services/city.service';
import { PropertyService } from 'src/app/services/property.service';
import { RoomService } from 'src/app/services/room.service';
import { RoomImageService } from 'src/app/services/room-image.service';
import { RoomTypeService } from 'src/app/services/room-type.service';

@Component({
  selector: 'app-host',
  templateUrl: './host.component.html',
  styleUrls: ['./host.component.css']
})
export class HostComponent {


  rooms: Room[] = [];

  address: string | null = null;
  addressNumber: string | null = null;
  description: string | null = null;
  accessInformation: string | null = null;
  cityForm = new FormControl('', [Validators.required]);
  cities : City[] = [];
  filtredCities: Observable<City[]> = new Observable<City[]>;
  roomType: RoomType | null = null;
  roomTypes: RoomType[] = [];
  filterRoomType: RoomType | null | undefined;
  longitude: number | null = null;
  latitude: number | null = null;

	basePricePerNight: number | null = null;
	defaultRoomImage: any = null;
  defaultImageToSent: any;
	imagesUrls: any[] = [];
	images: any[] = [];
	imagesToSent: any[] = [];
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
  pageIndex: number = 0;

  constructor(private message: SnackBarService, private cityService: CityService, private changeDetectorRef: ChangeDetectorRef,
              private roomTypeService: RoomTypeService, private authService: AuthService,  private roomService: RoomService,
              private roomImageService: RoomImageService, private propertyService: PropertyService, private availabilityService: AvailabilityService){
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

  public findRooms(){
    this.roomService.findOwnersRooms(this.authService.getLoggedInUser().id).subscribe({
      next: (response: Room[]) => {
        this.rooms = response;
        for(let i = 0; i < this.rooms.length; i++)
          if(this.rooms[i].defaultRoomImage != null)
            this.rooms[i].defaultRoomImage!.image = 'data:image/jpeg;base64,' + this.rooms[i].defaultRoomImage?.image;
       },
      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    })
  }

	save(){

		if(!this.validation())
			return;

		let city = this.cities.find(c => c.name === this.cityForm.value);

		const property = {
			id: null,
			owner: this.authService.getLoggedInUser(),
			description: this.description,
			address: this.address,
			addressNumber: this.addressNumber,
			accessInformation: this.accessInformation,
			rating: null,
			city: city,
			longitude: this.longitude,
			latitude: this.latitude,
		}

		this.propertyService.getProperty(this.authService.getLoggedInUser().id, this.cityForm.value, this.address, this.addressNumber).subscribe({
			next: (response) => {
				if(response != null)
					this.addNewRoom(response.id);
				else{
					this.propertyService.addNewProperty(property).subscribe({
						next: (response: any) => {
							this.addNewRoom(Number(response.body))
						},

						error: (error: HttpErrorResponse) => {
							if(error.status !== 200)
								this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
						}
					});
				}
			},

			error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
		});

	}

	addNewRoom(idProperty: number | null){
		this.defaultRoomImage = this.defaultRoomImage.slice('data:image/jpeg;base64,'.length);
		var room = {
			id: null,
			property: {id: idProperty},
			type: this.roomType,
			basePricePerNight: this.basePricePerNight,
      defaultRoomImage: null,
			description: this.description,
			numOfDoubleBeds: this.numOfDoubleBeds,
			numOfSingleBeds: this.numOfSingleBeds,
			numOfBedrooms: this.numOfBedrooms,
			numOfBathrooms: this.numOfBathrooms,
			minRentDays: this.minRentDays,
			hasTV: this.hasTV,
			hasPrivateBathroom: this.hasPrivateBathroom,
			hasAirCondition: this.hasAirCondition,
			hasWifi: this.hasWifi,
			hasKitchen: this.hasKitchen,
			hasJacuzzi: this.hasJacuzzi,
			areaSize: this.areaSize,
			capacity: this.capacity,
			petsAllowed: this.petsAllowed,
			hasParking: this.hasParking,
			hasElevator: this.hasElevator,
			smokingAllowed: this.smokingAllowed,
			partyAllowed: this.partyAllowed,
			numOfReviews: 0,
			averageReviews: 0
			// availabilities: [{id: null, startDate: this.startDate, endDate: this.endDate}]
		};

		this.roomService.addNewRoom(room).subscribe({
			next: (response) =>  {
        room.id = response.body
        this.saveNewAvailability(room);
        this.saveAllImages(room); 
			},

			error: (error: HttpErrorResponse) => {
				if(error.status !== 200)
					this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
			}
		});

	}

  saveNewAvailability(room: any){
    let newAvailability: Availability = {id: null, room: room, startDate: this.startDate, endDate: this.endDate};
    this.availabilityService.addNewAvailability(newAvailability).subscribe({
      next: (response) => { },

      error: (error: HttpErrorResponse) => {
				if(error.status !== 200)
					this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
			}
    });
  }

  saveAllImages(room: any){

    console.log("Image upload started");

    for(let image of this.imagesToSent){

      this.roomImageService.addNewRoomImage(room.id, image).subscribe({
        next: (reponse) => { },

        error: (error: HttpErrorResponse) => {
          if(error.status !== 200)
            this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
        }
      });
    }

    var roomImageDefault: RoomImageDefault = {id: room.id,  image: this.defaultImageToSent};

    this.roomImageService.addNewDefaultRoomImage(roomImageDefault).subscribe({
        next: (response) => {  },
        
        error: (error: HttpErrorResponse) => {
          if(error.status !== 200)
            this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
        }
    });

    this.clearInputs();
    this.message.info("Η καταχώρηση του δωματίου έγινε με επιτυχία!")
  }


  validation(): boolean{

	if(this.authService.getLoggedInUser() == null){
      this.message.warn("Δεν είστε συνδεδεμένος");
	  return false;
	}

    if(this.defaultRoomImage == null){
      this.message.warn("Πρέπει να προσθέσετε υποχρεωτικά μία κύρια φωτογραφία");
      return false;
    }

    if(this.roomType == null){
      this.message.warn("Πρέπει να επιλέξετε υποχρεωτικά τύπο δωματίου");
      return false;
    }

    if(this.cityForm.value == null){
      this.message.warn("Πρέπει να επιλέξετε υποχρεωτικά πόλη");
      return false;
    }

    if(this.address == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά διεύθυνση");
      return false;
    }

    if(this.addressNumber == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά αριθμό διεύθυνσης");
      return false;
    }

    if(this.latitude == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά γεωγραφικό πλάτος");
      return false;
    }

    if(this.longitude == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά γεωγραφικό μήκος");
      return false;
    }

    if(this.basePricePerNight == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά τιμή/βραδιά");
      return false;
    }

    if(this.basePricePerNight == null){
      this.message.warn("Πρέπει να συμπληρώσετε υποχρεωτικά μέγιστο αριθμό ατόμων");
      return false;
    }

    return true;
  }

  oncoordinatesSelected(event: any){
    this.latitude = event[0];
    this.longitude = event[1];
  }


  private filterCities(value: string): City[]{
    return this.cities.filter(city => city.name.toLowerCase().includes(value.toLowerCase()));
  }

  onAddDefaultImage(event: any){
    let file = event.target.files[0];
    this.defaultImageToSent = event.target.files[0];
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
      this.imagesToSent.push(file);
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

  public getLoggedInUser(): any{
    return this.authService.getLoggedInUser();
  }

  changePageEvent(e: PageEvent){
    this.pageIndex = e.pageIndex;
  }

  private formatDate(date: Date): string{
    var splitDate = date.toLocaleDateString().split('/');
    // return splitDate[2] + '-' + (splitDate[1].length === 1 ? '0' + splitDate[1] : splitDate[1]) + '-' + (splitDate[0].length === 1 ? '0' + splitDate[0] : splitDate[0]);
    return splitDate[2] + '-' + (splitDate[0].length === 1 ? '0' + splitDate[0] : splitDate[0]) + '-' + (splitDate[1].length === 1 ? '0' + splitDate[1] : splitDate[1]);
  }


  clearInputs(){
    this.address = null;
    this.addressNumber = null;
    this.roomType = null;
    this.basePricePerNight = 0;
    this.description = null;
    this.accessInformation = null;
    this.defaultRoomImage = null;
    this.numOfDoubleBeds = 0;
    this.numOfSingleBeds = 0;
    this.numOfBedrooms = 0;
    this.numOfBathrooms = 0;
    this.minRentDays = 0;
    this.hasTV = false;
    this.hasPrivateBathroom = false;
    this.hasAirCondition = false;
    this.hasWifi = false;
    this.hasKitchen = false;
    this.hasJacuzzi = false;
    this.areaSize = 0;
    this.capacity = 0;
    this.petsAllowed = false;
    this.hasParking = false;
    this.hasElevator = false;
    this.smokingAllowed = false;
    this.partyAllowed = false;
    this.images = [];
    this.longitude = null;
    this.latitude = null;
    this.startDate = new Date();
    this.endDate = new Date();
  }

}
