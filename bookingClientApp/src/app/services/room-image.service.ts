import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RoomImage } from '../model/room-image.model';

const httpOptions = {headers: new HttpHeaders({'Accept': 'application/json',
                                               'ContentType': 'application/json'})};

@Injectable({ providedIn: 'root' })
export class RoomImageService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getAllRoomImages(idRoom: number): Observable<RoomImage[]>{
    return this.http.get<RoomImage[]>(`${this.apiServerUrl}/roomImage/roomImages/${idRoom}`);
  }
}
