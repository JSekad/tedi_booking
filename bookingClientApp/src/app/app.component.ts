import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { Role } from '../app/Entities/Role';
import { RoleService } from '../app/Services/Role.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
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
}
