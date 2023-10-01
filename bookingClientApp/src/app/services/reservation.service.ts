import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Reservation } from '../model/reservation.model';

@Injectable({ providedIn: 'root' })
export class ReservationService {

  private apiServerUrl = 'https://localhost:443';

	constructor(private http: HttpClient) { }

	public saveReservation(newReservation: Reservation): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/reservation/add`, newReservation, {observe: 'response' });
	}

	public getAllReservations(): Observable<Reservation[]>{
		return this.http.get<Reservation[]>(`${this.apiServerUrl}/reservation/all`);
	}

	public findUserReservations(idGuest: number): Observable<Reservation[]>{
		return this.http.get<Reservation[]>(`${this.apiServerUrl}/reservation/findReservationsByIdGuest/${idGuest}`);
	}

	public updateReservations(reservation: Reservation): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/reservation/update`, reservation, {observe: 'response'});
	}
}
