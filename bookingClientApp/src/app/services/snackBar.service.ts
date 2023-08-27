import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({ providedIn: 'root' })
export class SnackBarService{

	constructor(private snackBar: MatSnackBar){ }

	info(message: string){
		this.snackBar.open(message, '', {duration: 3000, verticalPosition: 'top', panelClass: ['mat-snackBar-info']});
	}

	warn(message: string){
		this.snackBar.open(message, '', {duration: 3000, verticalPosition: 'top', panelClass: ['mat-snackBar-warn']});
	}

	error(message: string, action: string){
		this.snackBar.open(message, action, {duration: undefined, verticalPosition: 'top', panelClass: ['mat-snackBar-error']});
	}
	
}