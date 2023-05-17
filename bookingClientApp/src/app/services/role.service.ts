import { Role } from '../model/role';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RoleService{
    private apiServerUrl = 'http://localhost:8080';

    constructor(private http: HttpClient){ }

    public getRoles(): Observable<Role[]> {
        return this.http.get<Role[]>(`${this.apiServerUrl}/role/all`);
    }

    // public getRole(roleId: number): Observable<Role> {
    //     return this.http.get<Role>(`${this.apiServerUrl}/role`, roleId);
    // }
}
