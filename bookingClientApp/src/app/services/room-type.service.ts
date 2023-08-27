import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RoomType } from '../model/room-type.model';

@Injectable({ providedIn: 'root' })
export class RoomTypeService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getAllRoomTypes(): Observable<RoomType[]>{
    return this.http.get<RoomType[]>(`${this.apiServerUrl}/roomType/all`);
  }
}
