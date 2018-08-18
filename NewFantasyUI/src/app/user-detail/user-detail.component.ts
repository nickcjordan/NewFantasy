import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {User} from '../model/user';
import { AuthService } from "../service/auth.service";
import {UserService} from '../service/user.service';


@Component({
	selector: 'app-user-detail',
	templateUrl: './user-detail.component.html',
	styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

	@Input() user: User;

	constructor(
		private route: ActivatedRoute,
		private userService: UserService,
		private auth: AuthService, 
		private location: Location
	) {}

	ngOnInit() {
		this.getUser();
	}

	getUser(): void {
		const id = +this.route.snapshot.paramMap.get('id');
		this.userService.getUser(id).subscribe(user => this.user = user);
	}

	goBack(): void {
		this.location.back();
	}

	save(): void {
		this.userService.updateUser(this.user).subscribe(() => this.goBack());
	}
	
	authenticated() { 
		console.log('dashboard: checking authenticated --> ' + this.auth.authenticated);
		return this.auth.authenticated; 
	}

}
