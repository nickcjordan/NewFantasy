import { Component, OnInit, Injectable } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../model/user';
import { AuthService } from "../service/auth.service";
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-user-preview',
  templateUrl: './user-preview-page.component.html',
  styleUrls: ['./user-preview-page.component.scss']
})
export class UserPreviewPageComponent implements OnInit {
	
	user: User;
	userId: number;
	

  constructor(
	  private userService: UserService,
	  private route: ActivatedRoute,
	  private auth: AuthService
  ) {}

	ngOnInit() {
		this.setUser();
	}

	setUser(): void {
		this.userId = +this.route.snapshot.paramMap.get('id');
		this.userService.getUser(this.userId).subscribe(user => this.user = user);
	}
	
	authenticated() { 
		console.log('UserPreviewComponent: checking authenticated --> ' + this.auth.authenticated);
		return this.auth.authenticated; 
	}

}
