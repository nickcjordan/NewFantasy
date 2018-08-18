import { Component, OnInit, Injectable } from '@angular/core';
import {User} from '../../model/user';
import { AuthService } from "../../service/auth.service";
import {UserService} from '../../service/user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

	user: User;
	userId: number;
	
	constructor(
		private userService: UserService,
		private http: HttpClient,
		private auth: AuthService
	) {}

	ngOnInit() {
		this.setUser();
	}
	
	setUser(): void {
		this.userId = 0; // TODO grab user session id
		this.userService.getUser(this.userId).subscribe(user => this.user = user);
	}
	
	save(): void {
		this.userService.updateUser(this.user).subscribe();
	}
	
	authenticated() { 
		console.log('UserEditComponent: checking authenticated --> ' + this.auth.authenticated);
		return this.auth.authenticated; 
	}
}
