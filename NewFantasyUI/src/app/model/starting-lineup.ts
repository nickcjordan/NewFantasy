import { Player } from "./player";
import {PlayerList} from './player-list';

export class StartingLineup {
	
	qb: PlayerList;
	rb: PlayerList;
	wr: PlayerList;
	te: PlayerList;
	flex: PlayerList;
	k: PlayerList;
	dst: PlayerList;
	
	getAllPlayers(): Player[] {
		let players: Player[] = [];
		players.concat(this.qb.players);
		players.concat(this.rb.players);
		players.concat(this.wr.players);
		players.concat(this.te.players);
		players.concat(this.flex.players);
		players.concat(this.k.players);
//		players.concat(this.dst.players);
		return players;
	}
	
}
