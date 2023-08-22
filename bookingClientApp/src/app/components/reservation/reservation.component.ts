import { Component, Input } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { Reservation } from 'src/app/model/reservation.model';
import { Room } from './../../model/room.model';
import { RoomImage } from './../../model/room-image.model';
import { HostPhoto } from './../../model/host-photo.model';

import { ReservationService } from 'src/app/services/reservation.service';
import { RoomImageService } from '../../services/room-image.service';
import { HostPhotoService } from '../../services/host-photo.service';
import { SnackBarService } from 'src/app/services/snackBar.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {

  room: Room;
  roomDefaultImage: any;
  hostPhoto: any;
  numOfPersons: number;
  startDate: Date;
  endDate: Date;
  images: any[] = [];
  imagesSlider: any[] = [];

  constructor(private router: Router, private message: SnackBarService, private hostPhotoService: HostPhotoService,
              private roomImageService: RoomImageService, private reservationService: ReservationService){

    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      room: Room
      defaultRoomImage: any;
      numOfPersons: number;
      startDate: Date;
      endDate: Date;
    };

    this.room = state.room;
    this.roomDefaultImage = state.defaultRoomImage;
    this.numOfPersons = state.numOfPersons;
    this.startDate = state.startDate;
    this.endDate = state.endDate;

    this.hostPhotoService.getHostPhoto(this.room.property.owner.id).subscribe({
      next: (response: HostPhoto) => { this.hostPhoto = 'data:image/jpeg;base64,' + response.photo; },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });

    this.roomImageService.getAllRoomImages().subscribe({
      next: (response: RoomImage[]) => { this.images = response; },

      complete: () => {
        this.imagesSlider[0] = {image: this.roomDefaultImage, thumbImage: this.roomDefaultImage, title: ''};

        for(let i = 0; i < this.images.length; i++)
          this.imagesSlider[i + 1] = {image: 'data:image/jpeg;base64,' + this.images[i].image, thumbImage: 'data:image/jpeg;base64,' + this.images[i].image, title: ''} ;
      },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });

  }

  saveReservation(){
    const person = {id: 1, surname: 'Τσιμπος', name: 'Βασιλης'};
    const newReservation = new Reservation(this.room, person, this.numOfPersons, this.room.basePricePerNight, new Date(), null, this.startDate, this.endDate);
    this.reservationService.saveReservation(newReservation).subscribe( {

      error: (error: HttpErrorResponse) => {
        if(error.status === 200){
          this.message.info("Η κράτησή σας έγινε με επιτυχία!");
        }
        else{
          this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
        }

        this.router.navigate(['/search']);
      }

    });
  }

  private formatDate(date: Date): string{
    var splitDate = date.toLocaleDateString().split('/');
    return splitDate[2] + '-' + (Number(splitDate[0]) < 10 ? '0' + splitDate[0] : splitDate[0]) + '-' + (Number(splitDate[1]) < 10 ? '0' + splitDate[1] : splitDate[1])
  }

}
