import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {
  // title = 'bookingClientApp';
  // username: string;
  // password: string;

  constructor(){}

  // onSubmit(): void {
  //   // Handle form submission here
  //   console.log('Username:', this.username);
  //   console.log('Password:', this.password);
  //   // You can perform further actions, such as validating the input or making an API call
  //   // Once done, you can close the dialog
  //   this.dialogRef.close();
  // }

  ngOnInit(){
    document.getElementById("openPopup")!.addEventListener("click", function() {
      document.getElementById("popup")!.style.display = "block";
    });
    document.getElementById("closePopup")!.addEventListener("click", function() {
      document.getElementById("popup")!.style.display = "none";
    });

    document.getElementById("submitBtn")!.addEventListener("click", function() {
      const username = (<HTMLInputElement>document.getElementById("username")).value;
      const password = (<HTMLInputElement>document.getElementById("password")).value;
      console.log("UserName:", username);
      console.log("PassWord:", password);
      document.getElementById("popup")!.style.display = "none";
    });
  }

}
