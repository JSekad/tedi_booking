import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Room } from '../model/room.model';

const httpOptions = {headers: new HttpHeaders({'Accept': 'application/json', 
                                               'ContentType': 'application/json'})};

@Injectable({ providedIn: 'root' })
export class SearchService {
  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public getRooms(city: string | null, startDate: string | null, endDate: string | null, numPersons: number | null): Observable<Room[]>{
    return this.http.get<Room[]>(`${this.apiServerUrl}/room/searchAvailableRooms/${city}/${startDate}/${endDate}/${numPersons}`);
  }
}