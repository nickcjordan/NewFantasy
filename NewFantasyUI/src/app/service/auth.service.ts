import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';


@Injectable({
 providedIn: 'root',
})
export class AuthService {
	
	authentication = {}; //get from response to login
	authenticated = false;
	user = {};
	username: String;
	password: String;
	credentials = {};

  constructor(
	  private http: HttpClient,
	  private router: Router
  ) {  }
	
	authenticateDefault() {
		this.authenticate(this.credentials, () => { this.router.navigateByUrl('/');});
	}
	
	authenticate(credentials, callback) {
		this.credentials = credentials;
		this.username = credentials.username;
		this.password = credentials.password;
		console.log('AuthService :: enter : authenticated?:' + this.authenticated + ', username:' + this.username + ', password:' + this.password);
	    const headers = new HttpHeaders(credentials ? {
	        authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password),
			'X-Requested-With': 'XMLHttpRequest',
			'Access-Control-Allow-Origin': '*',
			'Access-Control-Allow-Headers': '*'
	    } : {});
		/*const headers = new HttpHeaders(credentials ? { authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password) } : {});*/
	
	    this.http.get(`${environment.uiServer}/auth`, {headers: headers}).subscribe(response => {
			this.authentication = response['authentication'];
			this.username = response['username'];
			this.authenticated = response['authenticated'];
			this.user= response['user'];
//	        if (response['authenticated']) {
//	            this.authenticated = true;
//	        } else {
//	            this.authenticated = false;
//	        }
			console.log('AuthService :: exit : authenticated?:' + this.authenticated + ', username:' + this.username + ', password:' + this.password);
	        return callback && callback();
	    });
	}
	
	logout() {
		this.authentication = {};
		this.username = '';
		this.authenticated = false;
		this.user = '';
		this.password = '';
		this.router.navigateByUrl('/login');
	}

}