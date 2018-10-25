import { Modifier } from "./modifier";
export class Player {
	playerId: string;
	playerName: string;
	playerPosition: string;
	teamName: string;
	onUserTeam: boolean;
	appliedModifier: Modifier;
}