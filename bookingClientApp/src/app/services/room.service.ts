import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Room } from '../model/room.model';

@Injectable({ providedIn: 'root' })
export class RoomService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public addNewRoom(newRoom: any): Observable<any>{
    return this.http.post<Room>(`${this.apiServerUrl}/room/newRoom`, newRoom, {observe : 'response'});
  }

  public findOwnersRooms(idOwner: number): Observable<Room[]>{
    return this.http.get<Room[]>(`${this.apiServerUrl}/room/findOwnersRooms/${idOwner}`);
  }
}
