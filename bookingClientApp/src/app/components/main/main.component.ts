import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  title = 'bookingClientApp';

  constructor(){}

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
