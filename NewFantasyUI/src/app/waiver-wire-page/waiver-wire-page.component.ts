import { Component, OnInit } from '@angular/core';
import {User} from '../model/user';
import {Player} from '../model/player';
import {UserService} from '../service/user.service';
import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import { PlayerService } from "../service/player.service";

@Component({
  selector: 'app-waiver-wire-page',
  templateUrl: './waiver-wire-page.component.html',
  styleUrls: ['./waiver-wire-page.component.scss']
})
export class WaiverWirePageComponent implements OnInit {
	
	currentUser: UserCredential;
	userId: number;
	private user: User;
	
	players: Player[];
	
//	RADIO BUTTON FLAGS
	showAvailablePlayers: boolean;
	showTakenPlayers: boolean;

	constructor(
		private userService: UserService,
		private playerService: PlayerService
	) { }

	ngOnInit() {
		this.setUser();
		this.setPlayers();
		this.showAvailablePlayers = true;
		this.showTakenPlayers = true;
	}
	
	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe(user => this.user = user);
	}
	
	setPlayers(): void {
		this.playerService.getAllPlayers().subscribe(players => this.players = players);
	}

//	function show1(){
//  document.getElementById('div1').style.display ='none';
//}
//function show2(){
//  document.getElementById('div1').style.display = 'block';
//}
}
