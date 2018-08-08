import { Component } from '@angular/core';
import { AuthService } from './service/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
//import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    model = {};

	constructor(private http: HttpClient, private router: Router, private auth:AuthService) {
		http.get('/ui/user').subscribe(model => this.model = model);
//		this.auth.authenticate(undefined, undefined);
		if(!this.auth.authenticated) {
			this.router.navigateByUrl('/login');
		}
	}
	
    logout() {
      this.http.post('logout', {});
      this.auth.authenticated = false;
      this.router.navigateByUrl('/login');
    }
	
	authenticated() { return this.auth.authenticated; }
}