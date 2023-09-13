import {Component, HostListener, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

import {AuthService} from "../../services/auth-service.service";
import {User} from "../../model/user.model";
import {ChatService} from "../../services/chat.service";
import { Chat } from '../../model/chat.model';
import {Router} from "@angular/router";



@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent {
  public alluser: any = [];
  check = sessionStorage.getItem('username');
  chatId: any = 0;
  chatObj: Chat = new Chat();
  public chatData: any = [];
  showUserDiv: boolean = false;

  loggedIn: boolean = false;
  luser: User = {} as User;

  toggleUserDiv() {
    if(!this.showUserDiv) {
      this.getAllUsersForChat();
      this.showUserDiv = !this.showUserDiv;
    }else{
      this.showUserDiv = false;
    }
  }

  constructor(public dialog:MatDialog, private authService: AuthService,private chatService: ChatService,private router: Router){
      this.authService.loggedInUserChange.subscribe(user => {
      this.loggedIn = !!user;
      this.luser = user as User;
      sessionStorage.setItem('username',this.luser.username);
    });
  }

  ngOnInit(){
    if (this.authService.getStoredJwtToken()!=null){

      this.authService.refreshPage();
    }
  }




  openLoginDialog(): void {
    this.dialog.open(LoginDialogComponent, {
      width: '600px',
      height: '650px',
      data: { username: '', password: '' }
    });
  }

  getAllUsersForChat(): void{
    // let all = setInterval(() => {
      this.luser.id

      this.chatService.getAll(this.luser.id).subscribe((data) => {

        this.alluser = data;
      })
    // }, 1000);

  }
  //TODO CHATS
  // goToChats(username: any) {
  //   this.chatService.getChatByFirstUserNameAndSecondUserName(username, sessionStorage.getItem("username")?? '').subscribe(
  //     (data) => {
  //       this.chatData = data;
  //       console.log(this.chatData)
  //     },
  //     (error) => {
  //       if (error.status == 404) {
  //         this.chatObj.firstUserName = sessionStorage.getItem("username")?? '';
  //         this.chatObj.secondUserName = username;
  //         this.chatObj.roomId = 2;
  //         this.chatService.createChatRoom(this.chatObj).subscribe(
  //           (data) => {
  //             this.chatData = data;
  //           })
  //       } else {
  //
  //       }
  //     });
  //
  // }

  //
  goToChat(username: any) {
    this.chatService.getChatByFirstUserNameAndSecondUserName(username, sessionStorage.getItem("username")?? '').subscribe(
      (data) => {
        this.chatId = data[0].chatId;
        sessionStorage.setItem("chatId", this.chatId);

        sessionStorage.setItem("gotochat", "false");
        this.router.navigateByUrl('/chat');
      },
      (error) => {
        if (error.status == 404) {
          this.chatObj.firstUserName = sessionStorage.getItem("username")?? '';
          this.chatObj.secondUserName = username;
          this.chatObj.roomId = 1;
          this.chatService.createChatRoom(this.chatObj).subscribe(
            (data) => {
              this.chatData = data;
              this.chatId = this.chatData.chatId;
              sessionStorage.setItem("chatId", this.chatData.chatId);

              sessionStorage.setItem("gotochat", "false");
              this.router.navigateByUrl('/chat');
            })
        } else {

        }
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
    this.alluser = null;
  }


}
