import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	
	credentials = {username: '', password: ''};

	constructor(private auth: AuthService, private http: HttpClient, private router: Router) {
   	}

  login() {
    this.auth.authenticate(this.credentials, () => { this.router.navigateByUrl('/');});
    return false;
  }

	ngOnInit() {
	}

}
