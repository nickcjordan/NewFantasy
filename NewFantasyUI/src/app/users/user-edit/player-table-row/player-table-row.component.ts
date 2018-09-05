import { Component, OnInit, Input } from '@angular/core';
import { UserCredential } from "../../../_models/user-credential";
import { Player } from "../../../model/player";
import {User} from '../../../model/user';

@Component({
  selector: 'app-player-table-row',
  templateUrl: './player-table-row.component.html',
  styleUrls: ['./player-table-row.component.css']
})
export class PlayerTableRowComponent implements OnInit {
	
	@Input() user: User;
	@Input() currentUser: UserCredential;
	@Input() userId: number;
	
  constructor() { }

  ngOnInit() {
  }

}
