import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

import {environment} from '../../environments/environment';
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class AuthenticationService {
	constructor(private http: HttpClient) {}

	login(username: string, password: string) {
		return this.http.post<any>(`${environment.uiServer}/auth/authenticate`, {username: username, password: password})
			.pipe(map(user => {
				if (user && user.token) {  // login successful if there's a jwt token in the response
					localStorage.setItem('currentUser', JSON.stringify(user));  // store user details and jwt token in local storage to keep user logged in between page refreshes
				}
				return user;
			}));
	}

	logout() {
		localStorage.removeItem('currentUser'); // remove user from local storage to log user out
	}
}