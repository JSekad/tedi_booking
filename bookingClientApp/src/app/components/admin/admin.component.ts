import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';
import exportFromJson, { ExportType } from 'export-from-json';

import { ReservationService } from 'src/app/services/reservation.service';
import { ReviewService } from 'src/app/services/review.service';
import { RoomService } from 'src/app/services/room.service';
import { UserService } from '../../services/user.service';
import { SnackBarService } from 'src/app/services/snackBar.service';

import { Reservation } from '../../model/reservation.model';
import { Room } from '../../model/room.model';
import { User } from '../../model/user.model';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

	users: User[] = [];
	displayedColumns: string[] = ['username', 'eponymo', 'onoma', 'select'];
	dataSource: any;
	selection = new SelectionModel<User>(true, []);

	constructor(private userService: UserService, private message: SnackBarService, private reviewService: ReviewService,
		private reservationService: ReservationService, private roomService: RoomService){ }

	ngOnInit(){
		this.userService.usersMeAitimaEggrafis().subscribe({
		next: (response: any) => {
			this.users = response;
			this.dataSource = new MatTableDataSource<User>(this.users);
			},

		error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
		});
	}

	approveSelectedOwners(){
		this.selection.selected.forEach(u => {
		u.approved = true;
		u.dateApproved = new Date();
		});

		var successfulApprovalCount = 0;

		for(let user of this.selection.selected){
			this.userService.updateUser(user).subscribe({
				error: (error: HttpErrorResponse) => {
					if(error.status != 200)
						this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
					else if(error.status === 200){
						successfulApprovalCount++;
						var index = this.users.indexOf(user, 0);
						if(index > -1)
							this.users.splice(index, 1);

						if(successfulApprovalCount == this.selection.selected.length){
							this.dataSource = new MatTableDataSource<User>(this.users);
							this.message.info("Οι χρήστες εγκρίθηκαν");
						}
					}
				}
			});
		}
	}

	isAllSelected(){
		const numSelectedUsers = this.selection.selected.length;
		const totalUsers = this.dataSource.data.length;
		return numSelectedUsers === totalUsers;
	}

	selectAllUsers(){
		if(this.isAllSelected()){
			this.selection.clear();
			return;
		}

		this.selection.select(...this.dataSource.data);
	}

	checkboxLabel(row?: User, rowIndex?: number): string{
		if(!row)
			return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
		if(rowIndex == null) rowIndex = 0;
			return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${rowIndex + 1}`;
	}

	exportRooms(type: ExportType){
		this.roomService.getAllRooms().subscribe({
			next: (response: Room[]) => {
				for(let room of response)
					room.defaultRoomImage = null;
				const data = response;
				const fileName = 'rooms';
				const exportType = type;
				exportFromJson({ data, fileName, exportType});
			}
		})
	}

	exportReservations(type: ExportType){
		this.reservationService.getAllReservations().subscribe({
			next: (response: Reservation[]) => {
				const data = response;
				const fileName = 'reservations';
				const exportType = type;
				exportFromJson({ data, fileName, exportType});
			}
		})
	}

	exportRoomReviews(type: ExportType){
		this.reviewService.getAllRoomReviews().subscribe({
			next: (response: Reservation[]) => {
				const data = response;
				const fileName = 'roomReviews';
				const exportType = type;
				exportFromJson({ data, fileName, exportType});
			}
		})
	}

	exportHostReviews(type: ExportType){
		this.reviewService.getAllHostReviews().subscribe({
			next: (response: Reservation[]) => {
				const data = response;
				const fileName = 'hostReviews';
				const exportType = type;
				exportFromJson({ data, fileName, exportType});
			}
		})
	}
}
