import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../model/chat.model';
import { Message } from '../model/message.model';
import { HttpClient } from '@angular/common/http';
import {User} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  baseUrl = "https://localhost:443";

  constructor(private httpClient: HttpClient) { }


  //TODO Να βρώ όλα τα chat
  getAll(id: number) {
    return this.httpClient.get<User[]>(this.baseUrl + "/chats/getallUsersForChat/"+id);
  }



  updateChat(message: Message, chatId: any): Observable<Object> {
    return this.httpClient.put(this.baseUrl + "/chats/message/" + `${chatId}`, message);
  }

  getChatById(chatId: any) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/temp/" + chatId)
  }

  addMessageToChatRoom(message: Message): Observable<Object> {
    return this.httpClient.post(this.baseUrl + "/chats/add/message1", message);
  }

  getAllMessagesByChatId(chatId: any) {
    return this.httpClient.get<Message[]>(this.baseUrl + "/chats/all/messages/from/chat/" + chatId)
  }

  createChatRoom(chat: Chat): Observable<Object> {
    return this.httpClient.post(this.baseUrl + "/chats/add1", chat);
  }

  getChatByFirstUserNameAndSecondUserName(firstUserName: String, secondUserName: String) {
    return this.httpClient.get<Chat[]>(this.baseUrl + "/chats/getChatByFirstUserNameAndSecondUserName" + '?firstUserName=' + firstUserName + '&secondUserName=' + secondUserName)
  }

  getChatByFirstUserNameOrSecondUserName(username: any) {
    return this.httpClient.get<Chat>(this.baseUrl + "/chats/getChatByFirstUserNameOrSecondUserName/" + username)
  }

}
