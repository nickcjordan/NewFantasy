import { Component, OnInit, Injectable } from '@angular/core';
import { AuthService } from './service/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
//import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
	

	constructor(
		private http: HttpClient, 
		private router: Router, 
		private auth: AuthService
	) { 
//		this.auth.authenticateDefault();
//		if (!this.auth.authenticated) { 
//			this.router.navigateByUrl('/login'); 
//			console.log('AppComponent: in constructor :: not authenticated');
//		} //route to login if not authenticated
	}
	
	authenticated() {
		if (!this.auth.authenticated) { 
			this.router.navigateByUrl('/login'); 
			console.log('AppComponent: in init :: not authenticated');
		} //route to login if not authenticated
		console.log('AppComponent: checking authenticated --> ' + this.auth.authenticated);
		return this.auth.authenticated; 
	}
}