import { UserCredential } from "../../_models/user-credential";
import { AlertService } from "../../_services/alert.service";
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Player } from "../../model/player";
import { Component, OnInit, Injectable } from '@angular/core';
import {User} from '../../model/user';
import { AuthService } from "../../service/auth.service";
import {UserService} from '../../service/user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from "@angular/router";

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
		private auth: AuthService,
        private alertService: AlertService,
		private router: Router
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
	
	movePlayerToBench(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.id, "MOVE_TO_BENCH");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
	movePlayerToLineupAtPosition(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.id, "START_AT_POSITION");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
	movePlayerToLineupAtFlex(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.id, "START_AT_FLEX");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/']); },
			error => { this.alertService.error(error); }
		);
	}
	
}
