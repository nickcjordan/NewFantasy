import { Component, OnInit } from '@angular/core';
import { AuthService } from './../service/auth.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
	
	title = 'Demo';
	greeting = {};

  constructor(private auth: AuthService, private http: HttpClient) {
  	http.get('resource').subscribe(data => this.greeting = data);
  }

  authenticated() { return this.auth.authenticated; }

  ngOnInit() {
  }

}