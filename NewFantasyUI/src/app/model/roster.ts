import {BenchPlayers} from './bench-players';
import { Player } from "./player";
import {StartingLineup} from './starting-lineup';

export class Roster {
	
	benchPlayers: BenchPlayers;
	startingLineup: StartingLineup;
	
	constructor(benchPlayers: BenchPlayers, startingLineup: StartingLineup){
       this.benchPlayers = benchPlayers;
		this.startingLineup = startingLineup;
   }
	
}
