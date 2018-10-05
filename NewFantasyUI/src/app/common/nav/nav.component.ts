import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {UserName} from '../../model/user-name';
import {UserService} from '../../service/user.service';
import {AuthService} from '../../service/auth.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
	selector: 'app-nav',
	templateUrl: './nav.component.html',
	styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

	userNames: UserName[];
	loggedIn: boolean;


	constructor(
		private userService: UserService,
		private auth: AuthService,
		private http: HttpClient,
		private router: Router
	) {
		this.router.onSameUrlNavigation = 'reload';
	}

	ngOnInit() {
		this.getUsers();
	}
	
	isLoggedIn() {
		return (JSON.parse(localStorage.getItem('currentUser')) != null);
	}

	getUsers(): void {
		this.userService.getAllUserNames().subscribe(users => this.userNames = users);
	}

	logout() {
		this.loggedIn = false;
		this.http.post('logout', {});
		this.auth.authenticated = false;
//		this.router.navigateByUrl('/login');
		location.assign('/login');
	}

}
