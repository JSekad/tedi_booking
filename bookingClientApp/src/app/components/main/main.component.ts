import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { Role } from '../../model/role';
import { RoleService } from '../../services/role.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  title = 'bookingClientApp';
  public roles: Role[];

  constructor(private roleService : RoleService){ this.roles = []; }

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
    this.getRoles();
  }

  public getRoles(): void{
    this.roleService.getRoles().subscribe(
      (response: Role[]) => {
        this.roles = response;
      },
      (error : HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  // dance() {
  //   alert("asdasdasdasdasd")
  // }
}
