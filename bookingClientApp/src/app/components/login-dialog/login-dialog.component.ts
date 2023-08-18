import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {AuthService} from "../../services/auth-service.service";
import {SnackBarService} from "../../services/snackBar.service";

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authService: AuthService,
    private message: SnackBarService,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  onLogin(): void {
    this.authService.login(this.data.username, this.data.password).subscribe(
      (response) => {
        if (response['access_token'] && response['access_token'].length < 100) {
          // The access_token contains the substring "error"
          this.message.warn(response['access_token']);
          console.log('Logged in:', response);
        } else {
          // Handle the successful login response here
          console.log('Logged in:', response);
          const decoded = this.authService.decodeJwtToken(response['access_token']);
          this.authService.storeJwtToken(response['access_token']);
          console.log('Decoded to:', decoded);
          this.dialogRef.close();
        }
      },
      (error) => {
        // Handle login error here
        console.error('Login failed:', error);
      }
    );
  }
}
