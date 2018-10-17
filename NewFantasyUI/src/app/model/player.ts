import { Modifier } from "./modifier";
export class Player {
	playerId: string;
	playerName: string;
	position: string;
	teamName: string;
	onUserTeam: boolean;
	appliedModifier: Modifier;
}