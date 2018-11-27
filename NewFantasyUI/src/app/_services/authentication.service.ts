import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

import {environment} from '../../environments/environment';

@Injectable()
export class AuthenticationService {
	constructor(private http: HttpClient) {}

	private headers = new HttpHeaders({
			'X-Requested-With': 'XMLHttpRequest',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Headers': '*'
	    });

	login(username: string, password: string) {
		return this.http.post<any>(`${environment.uiServer}/auth/authenticate`, {headers: this.headers, username: username, password: password})
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