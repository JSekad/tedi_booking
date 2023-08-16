import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {AuthService} from "../../services/auth-service.service";

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css']
})
export class LoginDialogComponent {
  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private authService: AuthService
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  onLogin(): void {
    this.authService.login(this.data.username, this.data.password).subscribe(
      (response) => {
        // Handle the successful login response here
        console.log('Logged in:', response);
      },
      (error) => {
        // Handle login error here
        console.error('Login failed:', error);
      }
    );
    this.onNoClick();
  }
}
