import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { AuthService } from '../../services/auth-service.service';
import {User} from "../../model/user.model";
import {formatDate} from "@angular/common";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {SnackBarService} from "../../services/snackBar.service";
import {Router} from "@angular/router";
import {RoomImageDefault} from "../../model/roomImageDefault.model";
import {HttpErrorResponse} from "@angular/common/http";
import {HostPhoto} from "../../model/host-photo.model";
import {HostPhotoService} from "../../services/host-photo.service";

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
  hostImage: any;
  imagesUrls: any[] = [];
  hostImageToSent: any;
  selectedRole: any;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private message: SnackBarService,
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef,
    private hostPhotoService: HostPhotoService
  ) {
    this.selectedRole = this.authService.selectedRole;
  }



  passwordMatchValidator(): boolean|null {
    if (this.user.password === this.confirmPassword) {
      return null; // Passwords match
    } else {
      return true; // Passwords do not match
    }
  }

  onAddHostImage(event: any){
      let file = event.target.files[0];
      this.hostImageToSent = event.target.files[0];
      var fileReader = new FileReader();
      fileReader.readAsDataURL(file);
      this.imagesUrls.push(file);
      this.changeDetectorRef.detectChanges();
      fileReader.onload = (event) => {
        const url = (<FileReader>event.target).result as string;
        this.hostImage = url;
        this.changeDetectorRef.detectChanges();
      }
  }

  ngOnInit(): void {
      this.user = this.authService.getLoggedInUser();
      console.log(this.user);
      let ok = this.parseDate(this.user.birthDate);
      this.selectedDate=ok?ok:new Date();
      this.hostPhotoService.getHostPhoto(this.user.id).subscribe({
        next: (response: HostPhoto) => { this.hostImage = 'data:image/jpeg;base64,' + response.photo; },
        error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
      });
  }

  onSubmitUpdatedUser(){
    console.log(this.user);
    this.userService.updateUserDetails(this.user).subscribe((response)=>{
      this.message.info("Τα στοιχεία του Χρήστη Άλλαξαν")
      this.authService.refreshPage()
      if(this.selectedRole.alias == 'owner'){

        var hostImageDefault: any = {id: this.user.id,  image: this.hostImageToSent};

        this.userService.updateImage(hostImageDefault).subscribe({
          next: (response) => {  },

          error: (error: HttpErrorResponse) => {
            if(error.status !== 200)
              this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
          }
        });

      }
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
