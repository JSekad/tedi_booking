import {Component, HostListener, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

import {AuthService} from "../../services/auth-service.service";
import {User} from "../../model/user.model";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent {

  loggedIn: boolean = false;
  luser: User = {} as User;

  constructor(public dialog:MatDialog, private authService: AuthService){
      this.authService.loggedInUserChange.subscribe(user => {
      this.loggedIn = !!user;
      this.luser = user as User;
    });
  }

  ngOnInit(){
    if (this.authService.getStoredJwtToken()) this.authService.refreshPage();
    console.log("refresh")
  }

  printUser(){
    console.log(this.luser);
  }

  openLoginDialog(): void {
    this.dialog.open(LoginDialogComponent, {
      width: '600px',
      height: '650px',
      data: { username: '', password: '' }
    });
  }

  // ngOnInit() {
  //   this.authService.loggedInUserObservable.subscribe(data => {
  //     console.log('here we are:',data)
  //     this.loggedIn = !!data;
  //   });
  // }

  logout(): void {
    this.authService.logoutUser();
  }


}
