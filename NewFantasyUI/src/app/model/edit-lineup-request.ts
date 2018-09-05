import { User } from "./user";
export class EditLineupRequest {
	
	playerId: string;
	userId: string;
	action: string;
	
	constructor(pId: string, uId: string, act: string) { 
		this.playerId = pId;
		this.userId = uId;
		this.action = act; 
	}
}