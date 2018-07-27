import {Player} from './player';

export class Team {
	id: string;
	name: string;
	qb: Player[];
	rb: Player[];
	wr: Player[];
	te: Player[];
	k: Player[];
}