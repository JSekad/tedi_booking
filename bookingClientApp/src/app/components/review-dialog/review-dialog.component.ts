import { Component, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {SnackBarService} from "../../services/snackBar.service";

import { AuthService } from "../../services/auth-service.service";
import { ReviewService } from "../../services/review.service";

import { Room } from 'src/app/model/room.model';
import { Person } from 'src/app/model/person.model';

@Component({
  selector: 'app-review-dialog',
  templateUrl: './review-dialog.component.html',
  styleUrls: ['./review-dialog.component.css']
})
export class ReviewDialogComponent {

	room: Room | null = null;
	owner: Person | null = null;
	reviewType: string = 'room';

	maxStars: number = 5;
	stars: number[] = [];
	starsIcon: string[] = [];
	rating: number = 1;
	reviewTitle: string = '';
	reviewText: string = '';

	constructor(private dialog: MatDialogRef<ReviewDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private authService: AuthService,
				 private reviewService: ReviewService, private message: SnackBarService){
		this.reviewType = data.type;
		if(this.reviewType === 'room')
			this.room = data.room;
		else
			this.owner = data.owner;

		for(let i = 0; i < this.maxStars; i++){
			this.stars.push(i);
			this.starsIcon.push('star_border');
		}
	}

	saveReview(){
		if(this.reviewType === 'room'){
			const review = {
							id: null, 
							reviewer: this.authService.getLoggedInUser(),
							room: this.room,
							rating: this.rating,
							title: this.reviewTitle,
							review: this.reviewText,
							sendTimestamp: new Date()
						};
			this.reviewService.saveNewRoomReview(review).subscribe({
				error:(error: HttpErrorResponse) => {
					if(error.status != 200)
						this.message.error("Προέκυψε σφάλμα", 'Έξοδος');
					else
						this.message.info("Η αξιολόγησή σας καταχωρήθηκε με επιτυχία!");
				}	
			});
		}
		else{
			const review = {
							id: null, 
							reviewer: this.authService.getLoggedInUser(),
							host: this.owner,
							rating: this.rating,
							title: this.reviewTitle,
							review: this.reviewText,
							sendTimestamp: new Date()
						};

			this.reviewService.saveNewHostReview(review).subscribe({
				error:(error: HttpErrorResponse) => {
					if(error.status != 200)
						this.message.error("Προέκυψε σφάλμα", 'Έξοδος');
					else
						this.message.info("Η αξιολόγησή σας καταχωρήθηκε με επιτυχία!");
				}	
			});
		}

		this.dialog.close();
	}

	onClick(index: number){
		this.rating = index;
		for(let i = 0; i < index; i++)
			this.starsIcon[i] = 'star';
		for(let i = index; i < this.maxStars; i++)
			this.starsIcon[i] = 'star_border';
	}

}
