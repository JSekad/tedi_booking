import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from "@angular/router";
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ReviewDialogComponent } from '../review-dialog/review-dialog.component';

import { AuthService } from "../../services/auth-service.service";
import { ReservationService } from 'src/app/services/reservation.service';
import { ReviewService } from 'src/app/services/review.service';
import { RoomService } from 'src/app/services/room.service';
import { SnackBarService } from 'src/app/services/snackBar.service';

import { Reservation } from 'src/app/model/reservation.model';
import { Room } from 'src/app/model/room.model';

@Component({
	selector: 'app-reservations-history',
	templateUrl: './reservations-history.component.html',
	styleUrls: ['./reservations-history.component.css']
})
export class ReservationsHistoryComponent {

	reviewDialogRef: MatDialogRef<ReviewDialogComponent> | null = null;

	reservations: Reservation[] = [];
	tableColumns: string[] = ['city', 'roomDescription', 'date', 'price', 'numOfPersons', 'owner', 'reservationDate', 'canselationDate', 'reviewRoom', 'reviewOnwer', 'cancelReservation'];
	roomHasReviewed = new Map<number, boolean>;
	hostHasReviewed = new Map<number, boolean>;

	today: string;

	constructor(private router : Router, private authService: AuthService, private roomService: RoomService,
				private reservationService: ReservationService, private message: SnackBarService,
				private reviewService: ReviewService, private reviewDialog: MatDialog){
		this.today = new Date().toISOString();
	}

	ngOnInit(){
		this.reservationService.findUserReservations(this.authService.getLoggedInUser().id).subscribe({
			next: (response: Reservation[]) => {
				this.reservations = response;
				this.reservations.forEach((reservation, index) => {
			        reservation.guest = this.authService.getLoggedInUser();
					var id: any = reservation.room;
					this.roomService.getRoomById(id).subscribe({
						next: (response: Room) => {

							reservation.room = response;

							var idReviewer = this.authService.getLoggedInUser().id;

							this.reviewService.findReviewForThisRoom(idReviewer, reservation.room.id).subscribe({
								error: (error: HttpErrorResponse) => {
									if(error.status != 200)
										this.roomHasReviewed.set(reservation.room.id, reservation.cancelationTimestamp != null || reservation.endDate.toString() >= this.today.split('T')[0]);
									else
										this.roomHasReviewed.set(reservation.room.id, true);

								}
							});

							this.reviewService.findReviewForThisHost(idReviewer, reservation.room.property.owner.id).subscribe({
								error: (error: HttpErrorResponse) => {
									if(error.status != 200)
										this.hostHasReviewed.set(reservation.room.property.owner.id, reservation.cancelationTimestamp != null || reservation.endDate.toString() >= this.today.split('T')[0]);
									else
										this.hostHasReviewed.set(reservation.room.property.owner.id, true);
								}
							});
						}
					});
				});
			},
			error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
		});
	}

	openRoomReviewDialog(room: Room){
		this.reviewDialogRef = this.reviewDialog.open(ReviewDialogComponent, {
			width: '500px',
			height: '400px',
			data: {
				room: room,
				type: 'room',
			},
		});

		this.reviewDialogRef.afterClosed().subscribe(
			result => { 
				if(result)
					this.roomHasReviewed.set(room.id, true);
			}
		);
	}

	openOwnerReviewDialog(owner: any){
		this.reviewDialogRef = this.reviewDialog.open(ReviewDialogComponent, {
			width: '500px',
			height: '400px',
			data: {
				owner: owner,
				type: 'owner',
			},
		});
		
		this.reviewDialogRef.afterClosed().subscribe(
			result => { 
				if(result)
					this.hostHasReviewed.set(owner.id, true);	
			}
		);
	}


	cancelReservation(reservation: Reservation){
		reservation.cancelationTimestamp = new Date();
		this.reservationService.updateReservations(reservation).subscribe({
			error:(error: HttpErrorResponse) => {
				if(error.status != 200)
					this.message.error("Προέκυψε σφάλμα", 'Έξοδος');
				else
					this.message.info("Η κράτησή σας ακυρώθηκε με επιτυχία!");
			 }
		})
	}

	canCancelReservation(reservation: Reservation): boolean{
		return reservation.startDate.toString() <= this.today.split('T')[0];
	}

	cancelButtonColor(reservation: Reservation){
		if(this.canCancelReservation(reservation))
			return 'color: grey';
		else
			return 'color: rgb(197, 38, 38)';
	}

	public goToSearchPage(){
		this.router.navigate(['/search']);
	}

}
