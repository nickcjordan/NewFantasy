import { NflTeam } from "./nfl-team";
import { Player } from "./player";
export class Modifier {
	
	modifierId: string;
	modifierName: string;
	modifierDescription: string;
	price: number;
	changePercentage: number;
	targetUserId: string;
	targetPlayerId: string;
	targetType: string;
	targetPosition: string;
	owningUserId: string;
	targetNflTeam: NflTeam;
	targetPlayers: Player[];
	
}
