import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {AuthService} from '../service/auth.service';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
	currentUser: UserCredential;
	userId: number;
	private user: User;

	constructor(
		private userService: UserService,
		private auth: AuthService,
		private credentialService: UserCredentialService
	) {}

	ngOnInit() {
		this.setUser();
	}

	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}

}