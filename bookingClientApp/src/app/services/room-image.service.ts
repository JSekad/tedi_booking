import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RoomImage } from '../model/room-image.model';
import { RoomImageDefault } from '../model/roomImageDefault.model';

const httpOptions = {headers: new HttpHeaders({'Accept': 'application/json',
                                               'ContentType': 'application/json'})};

@Injectable({ providedIn: 'root' })
export class RoomImageService {
    private apiServerUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    public getAllRoomImages(idRoom: number): Observable<RoomImage[]>{
		return this.http.get<RoomImage[]>(`${this.apiServerUrl}/roomImage/roomImages/${idRoom}`);
    }

    public addNewDefaultRoomImage(newImage: RoomImageDefault): Observable<any>{
		const formData: FormData = new FormData();
		formData.append('image', newImage.image);
		formData.append('idRoom', newImage.id.toString());
		return this.http.post<any>(`${this.apiServerUrl}/roomImageDefault/newDefaultImage`, formData, {observe : 'response'}); 
    }

    public addNewRoomImage(idRoom: number, image: any): Observable<any>{
		const formData: FormData = new FormData();
		formData.append('image', image);
		formData.append('idRoom', idRoom.toString());
		return this.http.post<any>(`${this.apiServerUrl}/roomImage/newImage`, formData, {observe : 'response'}); 
    }

	public updateDefaultRoomImage(idRoom: number, updatedImage: any){
		const formData: FormData = new FormData();
		formData.append('image', updatedImage);
		formData.append('idRoom', idRoom.toString());
		return this.http.post<any>(`${this.apiServerUrl}/roomImageDefault/updateDefaultImage`, formData, {observe : 'response'}); 
	}

    public deleteRoomImage(id: number): Observable<any>{
		return this.http.delete(`${this.apiServerUrl}/roomImage/removeById/${id}`);
  	}
}
