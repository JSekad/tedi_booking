import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Reservation } from '../model/reservation.model';

@Injectable({ providedIn: 'root' })
export class ReservationService {

  private apiServerUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public saveReservation(newReservation: Reservation): Observable<any>{
    return this.http.post<any>(`${this.apiServerUrl}/reservation/add`, newReservation, {observe: 'response' });
  }
}
