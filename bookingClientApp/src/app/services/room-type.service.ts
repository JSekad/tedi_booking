import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RoomType } from '../model/room-type.model';

@Injectable({ providedIn: 'root' })
export class RoomTypeService {

  private apiServerUrl = 'https://localhost:443';

  constructor(private http: HttpClient) { }

  public getAllRoomTypes(): Observable<RoomType[]>{
    return this.http.get<RoomType[]>(`${this.apiServerUrl}/roomType/all`);
  }
}
