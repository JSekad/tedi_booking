import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ReviewService {

	private apiServerUrl = 'http://localhost:8080';

	constructor(private http: HttpClient) { }

	public findReviewForThisRoom(idReviewer: number, idRoom: number): Observable<any>{
		return this.http.get<any>(`${this.apiServerUrl}/roomReview/findForThisRoom/${idReviewer}/${idRoom}`);
	} 

	public findReviewForThisHost(idReviewer: number, idHost: number): Observable<any>{
		return this.http.get<any>(`${this.apiServerUrl}/hostReview/findForThisHost/${idReviewer}/${idHost}`);
	} 

	public saveNewRoomReview(review: any): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/roomReview/add`, review, {observe: 'response'});
	}

	public saveNewHostReview(review: any): Observable<any>{
		return this.http.post<any>(`${this.apiServerUrl}/hostReview/add`, review, {observe: 'response'});
	}
}
