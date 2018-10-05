import { NflTeam } from "./nfl-team";
export class Modifier {
	
	modifierId: string;
	modifierName: string;
	modifierDescription: string;
	price: number;
	changePercentage: number;
	targetId: string;
	targetType: string;
	targetPosition: string;
	owningUserId: string;
	targetNflTeam: NflTeam;
	
}
