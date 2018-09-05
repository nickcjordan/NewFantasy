import { Component, OnInit, Input } from '@angular/core';
import { UserCredential } from "../../../_models/user-credential";
import { Player } from "../../../model/player";
import {User} from '../../../model/user';

@Component({
  selector: 'app-player-table-row-bench',
  templateUrl: './player-table-row-bench.component.html',
  styleUrls: ['./player-table-row-bench.component.css']
})
export class PlayerTableRowBenchComponent implements OnInit {
	
	@Input() user: User;
	@Input() currentUser: UserCredential;
	@Input() userId: number;

  constructor() { }

  ngOnInit() {
  }

}
