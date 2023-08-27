import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {AuthService} from "../../services/auth-service.service";
import {SnackBarService} from "../../services/snackBar.service";
import {FormControl} from "@angular/forms";


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
  ) {
    this.authService.loggedInUserChange.subscribe(user => { })
  }

  selectedDate: Date = new Date();

  userAboutToRegister =  {
    name: "",
    surame: "",
    birthDate: "",
    idNumber: "",
    email: "",
    phoneNumber: "",
    userName: "",
    password: "",
    roles:[]
  }

  rolesSelected= [];

  roles = [{alias:'user',name:'Χρήστης'},{alias:'owner',name:'Ιδιοκτήτης'}];

  passwordVisible = false;

  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onRegister(): void {
    this.authService.clearJwtToken();
    this.authService.register(this.userAboutToRegister).subscribe(
      (response) => {
        if (response['access_token'] && response['access_token'].length < 100) {
          // The access_token contains the substring "error"
          this.message.warn(response['access_token']);
        } else {
          // Handle the successful login response here
          this.dialogRef.close();
        }
      },
      (error) => {
        // Handle login error here
        console.error('Login failed:', error);
      }
    );
  }


  private formattedDate(): string {
    return this.selectedDate ? this.formatDate(this.selectedDate) : '';
  }

  updateBirthDate(event: any): void {
    this.userAboutToRegister.birthDate = this.formattedDate();
  }


  private formatDate(date: Date): string {
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return `${this.padZero(day)}/${this.padZero(month)}/${year}`;
  }

  private padZero(value: number): string {
    return value < 10 ? `0${value}` : `${value}`;
  }

  onLogin(): void {
    this.authService.clearJwtToken();
    this.authService.login(this.data.username, this.data.password).subscribe(
      (response) => {
        if (response['access_token'] && response['access_token'].length < 100) {
          // The access_token contains the substring "error"
          this.message.warn(response['access_token']);
        } else {
          // Handle the successful login response here
          this.authService.storeJwtToken(response['access_token']);
          this.authService.setLoggedInUser(this.authService.decodeJwtToken(response['access_token']));
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
