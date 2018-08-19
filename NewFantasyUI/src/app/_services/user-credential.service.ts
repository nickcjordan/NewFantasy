import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { UserCredential } from '../_models';

@Injectable()
export class UserCredentialService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<UserCredential[]>(`${environment.uiServer}/auth/users`);
    }

    getByUsername(username: string) {
        return this.http.get(`${environment.uiServer}/auth/users/` + username);
    }

    register(user: UserCredential) {
        return this.http.post(`${environment.uiServer}/auth/users/register`, user);
    }

    update(user: UserCredential) {
        return this.http.put(`${environment.uiServer}/auth/users/` + user.username, user);
    }

    delete(username: string) {
        return this.http.delete(`${environment.uiServer}/auth/users/` + username);
    }
}