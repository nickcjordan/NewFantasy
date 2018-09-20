import { Component, OnInit, Input, HostListener } from '@angular/core';
import {Player} from '../../model/player';
import {NflTeamData} from '../../model/nfl-team-data';
import {NflTeamOptions} from '../../model/nfl-team-options';
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
	keysToMatchOn:string[] = new Array("teamName")
	
	private sorted = false;
	searchText: string;
	
	teams: Array<NflTeamOptions> = NflTeamData.teams;
	public selectedValue: number;
	public selectedTeam: NflTeamOptions;
	
	faUserPlus = faUserPlus;
	faTimesCircle = faTimesCircle;
	faCheck = faCheck;
	faSearch = faSearch;
	numberOfResults: number;
	
	@Input() players: Player[];
	@Input() user: User;
	
//	availablePlayers: Player[];
//	unavailablePlayers: Player[];
	
	showAvailablePlayers: boolean;
	showTakenPlayers: boolean;
	
	showQb: boolean;
	showRb: boolean;
	showWr: boolean;
	showTe: boolean;
	showFlex: boolean;
	showK: boolean;
//	showDst: boolean;
	
	constructor(
		private userService: UserService,
		private router: Router
	) { }
	
	ngOnInit() {
		this.showAvailablePlayers = true;
		this.showTakenPlayers = false;
		this.showQb = true;
		this.showRb = true;
		this.showWr = true;
		this.showTe = true;
		this.showFlex = true;
		this.showK = true;
		this.numberOfResults = this.players.length;
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
//			return this.keysToMatchOn.some((key) => {  //  use this when you only want to sort by specific keys
				let val = obj[key];
				if (val == null) { return false; }
				if (typeof val === 'string' || val instanceof String) { return obj[key].includes(searchKey); }
				return false;
			});
		});
	}
	
	filterPlayersByAvailabilityFlags(arr: Player[]) {
		return arr.filter((p: Player) => {
				if (p.onUserTeam) { return this.showTakenPlayers; }
				if (!p.onUserTeam) { return this.showAvailablePlayers; }
				return false;
			});
	}
	
	filterPlayersByPositionFlags(arr: Player[]) {
		return arr.filter((p: Player) => {
				let pos = p.position;
				if (pos == "QB") { return this.showQb; }
				if (pos == "RB") { return this.showRb; }
				if (pos == "WR") { return this.showWr; }
				if (pos == "TE") { return this.showTe; }
				if (pos == "K") { return this.showK; }
				return false;
//				if (pos === "DST") { 
			});
	}
	
	filterPlayersByTeamSelect(arr: Player[]) {
		return arr.filter((obj: Player) => {
			return this.keysToMatchOn.some((key) => { 
				if (obj[key] == null) { return false; }
				if (typeof obj[key] === 'string' || obj[key] instanceof String) { return obj[key].includes(this.selectedTeam.abbrev); }
				return false;
			});
		});
	}
	
	search() {
		let res = this.filterPlayersByAvailabilityFlags(this.players);
		res = this.filterPlayersByPositionFlags(res);
		if (this.selectedValue != null && this.selectedTeam.abbrev != null) { res = this.filterPlayersByTeamSelect(res); }
		if (this.searchText) { res = this.filterPlayersBySearch(res, this.searchText); }
		this.numberOfResults = res.length;
		return res;
	}
	
	getSelectedValue(val: number) {
		if (val == 1) {
			this.selectedTeam = null;
		}
		this.selectedTeam = this.teams[val - 1];
	}
	
	toggleAvailable() { this.showAvailablePlayers = !this.showAvailablePlayers; }
	toggleUnavailable() { this.showTakenPlayers = !this.showTakenPlayers; }
	
	toggleShowQB() { this.showQb = !this.showQb; }
	toggleShowRB() { this.showRb = !this.showRb; }
	toggleShowWR() { this.showWr = !this.showWr; }
	toggleShowTE() { this.showTe = !this.showTe; }
	toggleShowK() { this.showK = !this.showK; }
	toggleShowFLEX() { 
			this.showFlex = !this.showFlex;
			this.showRb = this.showFlex;
			this.showWr = this.showFlex;
			this.showTe = this.showFlex;
		}
//		if (pos === "DST") { this.showDst = !this.showDst; }
	}


