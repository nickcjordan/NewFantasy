import { Component, OnInit, Input, HostListener } from '@angular/core';
import {Player} from '../../model/player';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Router } from "@angular/router";

import { faUserPlus } from '@fortawesome/free-solid-svg-icons';
import { faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { faSearch } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'player-table',
  templateUrl: './player-table.component.html',
  styleUrls: ['./player-table.component.scss']
})
export class PlayerTableComponent implements OnInit {
	
//	keysToMatchOn:string[] = ["playerName", "position", "teamName"]
	keysToMatchOn:string[] = new Array("playerName","position","teamName") 
	
	private sorted = false;
	searchText: string;
	
	faUserPlus = faUserPlus;
	faTimesCircle = faTimesCircle;
	faCheck = faCheck;
	faSearch = faSearch;
	
	@Input() players: Player[];
	@Input() user: User;
	
	availablePlayers: Player[];
	unavailablePlayers: Player[];
	
	showAvailablePlayers: boolean;
	showTakenPlayers: boolean;
	
	constructor(
		private userService: UserService,
		private router: Router
	) { }
	
	ngOnInit() {
		this.showAvailablePlayers = true;
		this.showTakenPlayers = true;
		this.setPlayerStatus();
	}
	
	setPlayerStatus() {
		this.availablePlayers = new Array();
		this.unavailablePlayers = new Array();
		for (let p of this.players) {
			if (p.onUserTeam) {
				this.unavailablePlayers.push(p);
			}
			if (!p.onUserTeam) {
				this.availablePlayers.push(p);
			}
		}
	}
	
	addPlayerToBench(player: Player) {
		let editLineupRequest = new EditLineupRequest(player.playerId, this.user.userId, "ADD_TO_BENCH");
		this.userService.editLineup(editLineupRequest).subscribe(
			data => { this.router.navigate(['/players']); },
		);
	}

	sortBy(by: string | any): void {
		this.players.sort((a: any, b: any) => {
			if (a[by] < b[by]) { return this.sorted ? 1 : -1; }
			if (a[by] > b[by]) { return this.sorted ? -1 : 1; }
			return 0;
		});
		this.sorted = !this.sorted;
	}
		
	filterPlayersBySearch(arr: Player[], searchKey: string) {
		return arr.filter((obj: Player) => {
			return Object.keys(obj).some((key) => {
//			return this.keysToMatchOn.some((key) => {
				let val = obj[key];
				if (val == null) {
					return false;
				}
				if (typeof val === 'string' || val instanceof String) {
					return obj[key].includes(searchKey);
				}
				return false;
			});
		});
	}
	
	filterPlayersByFlags(arr: Player[]) {
		return arr.filter((p: Player) => {
				if (p.onUserTeam) {
					console.log(p.playerName + " is taken");
					return this.showTakenPlayers;
				}
				if (!p.onUserTeam) {
//					console.log(p.playerName + " is available");
					return this.showAvailablePlayers;
				}
				return false;
			});
	}
	
	search() {
		if (!this.searchText) { return this.filterPlayersByFlags(this.players); }
		if (this.searchText) { return this.filterPlayersBySearch(this.filterPlayersByFlags(this.players), this.searchText); }
	}
	
	toggleAvailable() {
		console.log("available: " + this.showAvailablePlayers + " --> " + !this.showAvailablePlayers);
		this.showAvailablePlayers = !this.showAvailablePlayers;
	}
	
	toggleUnavailable() {
		console.log("taken: " + this.showTakenPlayers + " --> " + !this.showTakenPlayers);
		this.showTakenPlayers = !this.showTakenPlayers;
	}

}
