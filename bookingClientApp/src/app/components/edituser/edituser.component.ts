import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import {User} from "../../model/user.model";
import {formatDate} from "@angular/common";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {SnackBarService} from "../../services/snackBar.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  user: any = {};
  selectedDate: Date = new Date();
  // passwordForm: FormGroup;
  confirmPassword: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private message: SnackBarService,
    private router: Router) {
    // this.passwordForm = this.fb.group({
    //   password: ['', [Validators.required]],
    //   confirmPassword: ['', [Validators.required]],
    // }, {
    //   validator: this.passwordMatchValidator,
    // });
  }

  passwordMatchValidator(): boolean|null {
    if (this.user.password === this.confirmPassword) {
      return null; // Passwords match
    } else {
      return true; // Passwords do not match
    }
  }

  ngOnInit(): void {
      this.user = this.authService.getLoggedInUser();
      console.log(this.user);
      let ok = this.parseDate(this.user.birthDate);
      this.selectedDate=ok?ok:new Date();
  }

  onSubmitUpdatedUser(){
    console.log(this.user);
    this.userService.updateUserDetails(this.user).subscribe((response)=>{
      this.message.info("Τα στοιχεία του Χρήστη Άλλαξαν")
      this.authService.refreshPage()
    },error => {
      this.message.info("Προέκυψε κάποιο πρόβλημα")
    },);
  }

  routeX() {
    this.router.navigate(['/']);
  }

  onSubmitPassword(){
    console.log(this.user.password);
    console.log(this.confirmPassword);
    if(this.passwordMatchValidator()) {
      this.message.warn("Οι κωδικοί δεν είναι ίδιοι");
      return;
    }
    let obj = {
      userId: this.user.id,
      password: this.user.password
    }
    this.userService.changePassWord(obj).subscribe((response)=>{
      this.message.info("Ο κωδικός Άλλαξε")
    },error => {
      this.message.info("Ο κωδικός έχει Πρόβλημα")
    },)
  }

  private formattedDate(): string {
    return this.selectedDate ? this.formatDate(this.selectedDate) : '';
  }

  updateBirthDate(event: any): void {
    this.user.birthDate = this.formattedDate();
  }

  private parseDate(dateString: String) {

    const parts = dateString.split('/');
    if (parts.length === 3) {
      const day = parseInt(parts[0], 10);
      const month = parseInt(parts[1], 10) - 1; // Months are zero-based (0 = January)
      const year = parseInt(parts[2], 10);
      return new Date(year, month, day);
    }
    return null; // Invalid date format
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

  onSubmit() {
    console.log(this.user)
  }
}
