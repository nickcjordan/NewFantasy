import { UserCredential } from "../_models";
import { UserCredentialService } from "../_services/user-credential.service";
import { Modifier } from "../model/modifier";
import { ModifierUpdateRequest } from "../model/modifier-update-request";
import { NflTeam } from "../model/nfl-team";
import { Player } from "../model/player";
import { Roster } from "../model/roster";
import { StartingLineup } from "../model/starting-lineup";
import {Component, OnInit} from '@angular/core';
import {User} from '../model/user';
import {UserService} from '../service/user.service';
import {AuthService} from '../service/auth.service';
import { ModifierService } from "../service/modifier.service";
import { faCoins, faPercent, faArrowAltCircleUp, faArrowAltCircleDown } from '@fortawesome/free-solid-svg-icons';
import { Observable, Subscription } from "rxjs";


@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard-page.component.html',
	styleUrls: ['./dashboard-page.component.scss']
})
export class DashboardPageComponent implements OnInit {

	faCoins = faCoins;
	faPercent = faPercent;
	faArrowAltCircleUp = faArrowAltCircleUp;
	faArrowAltCircleDown = faArrowAltCircleDown;

	currentUser: UserCredential;
	userId: number;
	private user: User;
	private opponent: User;

	constructor(
		private userService: UserService,
		private auth: AuthService,
		private modifierService: ModifierService,
		private credentialService: UserCredentialService
	) {}

	ngOnInit() {
		this.setUser();
	}
	
	applyModifierToPlayer(modifier, player): void {
		this.modifierService.sendModifierRequest(new ModifierUpdateRequest(this.user.userId, modifier.modifierId, player.playerId, "APPLY")).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
		);
	}
	
	removeModifierFromPlayer(modifier, player): void {
		this.modifierService.sendModifierRequest(new ModifierUpdateRequest(this.user.userId, modifier.modifierId, player.playerId, "REMOVE")).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
		);
	}
	
	
	setModifierTargets(): void {
		for (let modifier of this.user.modifiers) {
			modifier.targetPlayers = this.getTargetPlayers(modifier);
		}
			
	}

	setUser(): void {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.userService.getUser(this.currentUser.id).subscribe((user: User) => {
			this.user = user;
			this.setOpponent();
		});
	}
	
	setOpponent(): void {
		this.userService.getUser(this.user.matchupSchedule.scheduleByWeek["5"].opponentUserId).subscribe((user: User) => this.opponent = user);
	}
	
	sellModifier(selectedModifier: Modifier): void {
		this.modifierService.sendModifierRequest(new ModifierUpdateRequest(this.user.userId, selectedModifier.modifierId, null, "SELL")).subscribe(
			data => {location.reload();},
			error => {console.log(error)}
		);
	}
	
	getTargetPlayers(selectedModifier: Modifier): Player[] {
		let roster: Roster = (selectedModifier.changePercentage > 0) ? this.user.team.roster : this.opponent.team.roster;
		return this.filterTargetPlayers(selectedModifier, this.getAllPlayers(roster));
	} 

	filterTargetPlayers(mod: Modifier, targetPlayers: Player[]): Player[] {
		for (let p of targetPlayers) {
			console.log("target= " + p.playerName);
		}
		if ((targetPlayers == null) || (targetPlayers.length === 0)) {return targetPlayers;}
		let filtered: Player[] = targetPlayers;
		if (mod.targetNflTeam != null) {	filtered = this.filterTargetPlayersByNflTeam(mod.targetNflTeam, filtered); } else {console.log("no target nfl team: " + mod.targetNflTeam);}
		if (mod.targetPosition != null) {	filtered = this.filterTargetPlayersByPosition(mod.targetPosition, filtered); } else {console.log("no target position: " + mod.targetPosition);}
		return filtered;
	}
	
	filterTargetPlayersByNflTeam(nflTeam: NflTeam, arr: Player[]): Player[] {
		if ((arr == null) || (arr.length === 0)) {return arr;}
		return arr.filter((player: Player) => {
			return (player.teamName === nflTeam.abbreviation);
		});
	}
	
	filterTargetPlayersByPosition(position: string, arr: Player[]): Player[] {
		if ((arr == null) || (arr.length === 0)) {return arr;}
		return arr.filter((player: Player) => {
			return (position.startsWith(player.position.charAt(0)));
		});
	}
	
	getAllPlayers(roster: Roster): Player[] {
		return this.getAllPlayersFromLineup(roster.startingLineup).concat(roster.benchPlayers.players);
	}
	
	getAllPlayersFromLineup(lineup: StartingLineup): Player[] {
		return lineup.qb.players.concat(lineup.rb.players).concat(lineup.wr.players).concat(lineup.te.players).concat(lineup.flex.players).concat(lineup.k.players) //.concat(lineup.dst.players);
	}
	
}




