import {Component, HostListener, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';

import {AuthService} from "../../services/auth-service.service";
import {User} from "../../model/user.model";
import {ChatService} from "../../services/chat.service";
import { Chat } from '../../model/chat.model';
import {Router} from "@angular/router";
import {SnackBarService} from "../../services/snackBar.service";
import {Role} from "../../model/role";



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
      this.alluser = null;
      this.showUserDiv = false;
    }
  }

  constructor(public dialog:MatDialog, private authService: AuthService,private chatService: ChatService,private router: Router, private message: SnackBarService){
      this.authService.loggedInUserChange.subscribe(user => {
      this.loggedIn = !!user;
      this.luser = user as User;
      sessionStorage.setItem('username',this.luser?.username);
    });
      this.authService.selectedRoleChange.subscribe(role =>{
        if(role?.alias === 'owner'){
          this.router.navigate(['/host']);
        } else if(role.alias === 'admin'){
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/'])
        }

      })
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

  selectRole(role:any){
    if (role.alias === 'owner' && (!this.luser.approved)){
      this.message.warn("Δεν έχετε εγγρηθεί από τον admin");
      return;
    }
    this.authService.selectedRoleChange.next(role);
    this.authService.selectedRole = role;
  }

  getAllUsersForChat(): void{
    // let all = setInterval(() => {
    sessionStorage.setItem('username',this.luser.username);
      this.luser.id

      this.chatService.getAll(this.luser.id).subscribe((data) => {

        this.alluser = data;
      })
    // }, 1000);

  }
  // TODO CHATS
  goToChats(username: any) {
    this.chatService.getChatByFirstUserNameAndSecondUserName(username, sessionStorage.getItem("username")?? '').subscribe(
      (data) => {
        this.chatData = data;
        console.log(this.chatData)
      },
      (error) => {
        this.message.warn("Δεν έχετε συνομιλίες");
      });

  }


  goToChat(chatId: any) {
    this.chatId = chatId
    sessionStorage.setItem("chatId", chatId);
    // sessionStorage.setItem("gotochat", "false");
    this.router.navigate(['/chat']);
    this.alluser = null;
    this.chatData = null;
    this.showUserDiv = false;
  }


  logout(): void {
    this.authService.logoutUser();
    this.alluser = null;
  }

  goToEdit(): void {
    console.log("EDIT")
    this.router.navigate(['/edituser']);
  }


}
