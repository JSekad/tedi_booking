import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Chat } from '../../model/chat.model';
import { Message } from '../../model/message.model';
import { ChatService } from '../../services/chat.service';
import { UserService } from '../../services/user.service';
import {AuthService} from "../../services/auth-service.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  chatForm: FormGroup;
  chatObj: Chat = new Chat();
  messageObj: Message = new Message();
  public messageList: any = [];
  public chatList: any = [];
  replymessage: String = "checking";
  public chatData: any;
  msg = "Good work";
  chatId: any = sessionStorage.getItem('chatId');
  color = "";
  secondUserName = "";
  public alluser: any = [];
  check = sessionStorage.getItem('username');


  private intervalId: any;


  firstUserName = sessionStorage.getItem('username');
  senderEmail = sessionStorage.getItem('username');
  senderCheck = sessionStorage.getItem('username');

  constructor(
    private chatService: ChatService,
    private router: Router,
    private userService: UserService,
    private cdref: ChangeDetectorRef,
    private authService: AuthService) {

    this.chatForm = new FormGroup({
      replymessage: new FormControl()
    });

  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
  }


  ngOnInit(): void {
    this.intervalId = setInterval(() => {
      this.chatService.getChatById(sessionStorage.getItem('chatId')).subscribe(data => {
        this.chatData = data;
        this.secondUserName = this.chatData.secondUserName;
        this.firstUserName = this.chatData.firstUserName;


        this.chatService.getAllMessagesByChatId(this.chatId).subscribe(data => {
          // console.log(data);
          this.chatData = data;
          this.messageList = this.chatData;
        });
      });

    }, 1000);


    this.cdref.detectChanges();

  }

  sendMessage() {
    console.log(this.chatForm.value);

    this.messageObj.replymessage = this.chatForm.value.replymessage;
    this.messageObj.senderEmail = this.senderEmail ?? '';
    this.messageObj.chat = {id:this.chatId};
    this.chatService.addMessageToChatRoom(this.messageObj).subscribe(data => {
      this.chatForm.reset();

      this.chatService.getAllMessagesByChatId(this.chatId).subscribe(data => {
        this.chatData = data;
        this.messageList = this.chatData.messageList;
        this.secondUserName = this.chatData.secondUserName;
        this.firstUserName = this.chatData.firstUserName;

      })
    });

  }

  routeX() {
    sessionStorage.clear();
    clearInterval(this.intervalId);
    // window.location.reload();
    this.router.navigateByUrl('/search');
  }

}
