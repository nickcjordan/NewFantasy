import { Component, OnInit } from '@angular/core';
import { User} from '../model/user';
import { UserService } from '../service/user.service';
import { AuthService } from '../service/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
	userId: number;
	private user: User;
//    users: User[] = [];

  constructor(
	  private userService: UserService, 
	  private auth: AuthService,
	  private router: Router, 
	  private http: HttpClient
  ) {}

  ngOnInit() {
//      this.getUsers();
	  this.setUser();
  }

//  getUsers(): void {
//    this.userService.getUsers().subscribe(users => this.users = users);
//  }
	
	setUser(): void {
		this.userId = 0; // TODO grab user session id
		this.userService.getUser(this.userId).subscribe(user => this.user = user);
	}
	
	authenticated() { 
		console.log('dashboard: checking authenticated --> ' + this.auth.authenticated);
		if (!this.auth.authenticated) {
			this.router.navigateByUrl('/login');
		}
		return this.auth.authenticated; 
	}
}