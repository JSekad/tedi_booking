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

  dance() {
    alert("asdasdasdasdasd")
  }
}
