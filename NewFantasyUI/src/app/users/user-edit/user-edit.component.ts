import { UserCredential } from "../../_models/user-credential";
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
	currentUser: UserCredential;
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
	
	save(): void {
		this.userService.updateUser(this.user).subscribe();
	}
	
	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
}
