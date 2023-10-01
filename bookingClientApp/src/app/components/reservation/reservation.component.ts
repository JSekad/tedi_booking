import { Component, Input } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { Reservation } from 'src/app/model/reservation.model';
import { Room } from './../../model/room.model';
import { RoomImage } from './../../model/room-image.model';
import { HostPhoto } from './../../model/host-photo.model';
import { User } from './../../model/user.model';

import { AuthService } from 'src/app/services/auth-service.service';
import { ReservationService } from 'src/app/services/reservation.service';
import { RoomImageService } from '../../services/room-image.service';
import { HostPhotoService } from '../../services/host-photo.service';
import { SnackBarService } from 'src/app/services/snackBar.service';
import {Chat} from "../../model/chat.model";
import {ChatService} from "../../services/chat.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {

  user: User | null;
  room: Room;
  hostPhoto: any;
  numOfPersons: number;
  startDate: Date;
  endDate: Date;
  images: any[] = [];
  imagesSlider: any[] = [];
  chat: Chat = new Chat();
  public chatData: any = [];
  math = Math;

  constructor(private router: Router, private message: SnackBarService, private hostPhotoService: HostPhotoService,
              private roomImageService: RoomImageService, private reservationService: ReservationService,
              private authService: AuthService,private chatServive: ChatService){
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      user: User;
      room: Room
      defaultRoomImage: any;
      numOfPersons: number;
      startDate: Date;
      endDate: Date;
    };

    this.user = state.user;
    this.room = state.room;
    this.numOfPersons = state.numOfPersons;
    this.startDate = state.startDate;
    this.endDate = state.endDate;
  }

  ngOnInit(){

    this.hostPhotoService.getHostPhoto(this.room.property.owner.id).subscribe({
      next: (response: HostPhoto) => { this.hostPhoto = 'data:image/jpeg;base64,' + response.photo; },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });

    this.roomImageService.getAllRoomImages(this.room.id).subscribe({
      next: (response: RoomImage[]) => { this.images = response; },

      complete: () => {
        this.imagesSlider[0] = {image: this.room.defaultRoomImage?.image , thumbImage: this.room.defaultRoomImage?.image, title: ''};

        for(let i = 0; i < this.images.length; i++)
          this.imagesSlider[i + 1] = {image: 'data:image/jpeg;base64,' + this.images[i].image, thumbImage: 'data:image/jpeg;base64,' + this.images[i].image, title: ''} ;
      },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });

  }

  saveReservation(){
    if(this.user == null)
      return;
    const person = {id: this.user?.id, surname: this.user?.surname, name: this.user?.name, birthDate: this.user?.birthDate, idNumber: this.user?.idNumber, email: this.user?.email, phoneNumber: this.user?.phoneNumber, approved: this.user?.approved, dateApproved: this.user?.dateApproved, numOfReviews: this.user?.numOfReviews, averageReviews: this.user?.averageReviews };
    this.room.defaultRoomImage = null;
    const newReservation: Reservation = {id: null, room: this.room,  guest: person, numOfPersons: this.numOfPersons, price: this.room.basePricePerNight, reservationTimestamp: new Date(), cancelationTimestamp: null, startDate: this.startDate, endDate: this.endDate};
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
    return splitDate[2] + '-' + (splitDate[1].length === 1 ? '0' + splitDate[1] : splitDate[1]) + '-' + (splitDate[0].length === 1 ? '0' + splitDate[0] : splitDate[0]);
  }


  chatWith():void{
    this.chat.firstUserName = this.authService.getLoggedInUser()? `${this.authService.getLoggedInUser().id}` : '';
    this.chat.secondUserName = this.room.property.owner.id ? `${this.room.property.owner.id}` : '';
    this.chat.roomId = this.room.id;
    this.chatServive.createChatRoom(this.chat).subscribe(
      (data) => {
        this.chatData = data;
        console.log(this.chatData)
        sessionStorage.setItem("chatId", this.chatData.chatId);
        // sessionStorage.setItem("gotochat", "false");
        this.router.navigateByUrl('/chat');
      })
  }

  goToSearchPage(){
    this.router.navigate(['/search']);
  }
}
