import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ReviewService {

	private apiServerUrl = 'https://localhost:443';

	constructor(private http: HttpClient) { }

	public getAllRoomReviews(): Observable<any[]>{
		return this.http.get<any[]>(`${this.apiServerUrl}/roomReview/all`);
	}

	public getAllHostReviews(): Observable<any[]>{
		return this.http.get<any[]>(`${this.apiServerUrl}/hostReview/all`);
	}

	public findReviewForThisRoom(idReviewer: number, idRoom: number): Observable<any>{
		return this.http.get<any>(`${this.apiServerUrl}/roomReview/findForThisRoom/${idReviewer}/${idRoom}`);
	}

	public findReviewForThisHost(idReviewer: number, idHost: number): Observable<any>{
		return this.http.get<any>(`${this.apiServerUrl}/hostReview/findForThisHost/${idReviewer}/${idHost}`);
	}

	public saveNewRoomReview(review: any): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/roomReview/addRoomReview`, review, {observe: 'response'});
	}

	public saveNewHostReview(review: any): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/hostReview/addHostReview`, review, {observe: 'response'});
	}
}
