import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

import {AuthService} from "../../services/auth-service.service";


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent {

  loggedIn: boolean = false;
  // private subscription: Subscription;

  constructor(public dialog:MatDialog, private authService: AuthService){
    // this.subscription = this.authService.loggedInUserObservable.subscribe(data => {
    //   this.loggedIn = !!data;
    //   console.log('hello',data);
    // });
  }



  openLoginDialog(): void {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '250px',
      data: { username: '', password: '' }
    });
  }

  ngOnInit() {
    this.authService.loggedInUserObservable.subscribe(jwt => {
      console.log('here we are:',jwt)
      this.loggedIn = !!jwt;
    });
  }

  logout(): void {
    this.authService.logoutUser();
  }


}
