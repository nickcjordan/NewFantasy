import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import { AuthService } from '../../service/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  users: User[];

	constructor(private userService: UserService, private auth: AuthService, private http: HttpClient, private router: Router) {}

	ngOnInit() {
		this.getUsers();
	}
	
	getUsers(): void {
		this.userService.getUsers().subscribe(users => this.users = users);
	}
	
    logout() {
      this.http.post('logout', {});
      this.auth.authenticated = false;
      this.router.navigateByUrl('/login');
    }

}
