import { Message } from "./message.model";

export class Chat {

  chatId: number = 0;
  roomId: number = 0;
  firstUserName: string = '';
  secondUserName: string = '';
  messageList: Message[] = [];

  constructor() {
    // Initialize properties here if needed.
    // For example:
    // this.chatId = 0;
    // this.roomId = 0;
    // this.firstUserName = '';
    // this.secondUserName = '';
    // this.messageList = [];
  }
}


