import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import { UserCredential } from "../../_models/user-credential";
import { AlertService } from "../../_services/alert.service";
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Player } from "../../model/player";

@Component({
  selector: 'app-bench-edit',
  templateUrl: './bench-edit.component.html',
  styleUrls: ['./bench-edit.component.css']
})
export class BenchEditComponent implements OnInit {
	
	currentUser: UserCredential;
	user: User;
	userId: number;

  constructor(
		private userService: UserService,
        private alertService: AlertService,
		private router: Router
  ) { }

	ngOnInit() {
		this.setUser();
	}
	
	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
}
