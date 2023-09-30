import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../model/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {

  private apiServerUrl = 'https://localhost:443';

  constructor(private http: HttpClient) { }

  public usersMeAitimaEggrafis(): Observable<any>{
    return this.http.get<any>(`${this.apiServerUrl}/user/usersMeAitimaEggrafis`);
  }

  public updateUser(user: User): Observable<any>{
    const formData: FormData = new FormData();
    formData.append('idUser', user.id.toString());
    return this.http.post<any>(`${this.apiServerUrl}/user/approveUser`, formData, { observe: 'response' });
  }

  public updateUserDetails(user: User){
    return this.http.post<any>(`${this.apiServerUrl}/user/updateDetails`, user);
  }

  changePassWord(password:any){
    return this.http.post<any>(`${this.apiServerUrl}/user/changePassWord`,password)
  }

  // getAll() {
  //   return this.http.get<User[]>(this.apiServerUrl + "/user/getall")
  // }


}
