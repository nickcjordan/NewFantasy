import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {AuthService} from '../service/auth.service';
import { faCoins } from '@fortawesome/free-solid-svg-icons';
import {faPercent} from '@fortawesome/free-solid-svg-icons';


@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard-page.component.html',
	styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {
	
	faCoins = faCoins;
	faPercent = faPercent;

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