import { Component, OnInit, Input } from '@angular/core';
import { Router } from "@angular/router";
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import { UserCredential } from "../../_models/user-credential";
import { AlertService } from "../../_services/alert.service";
import { EditLineupRequest } from "../../model/edit-lineup-request";
import { Player } from "../../model/player";

@Component({
  selector: 'app-bench-edit',
  templateUrl: './bench-edit.component.html',
  styleUrls: ['./bench-edit.component.scss']
})
export class BenchEditComponent implements OnInit {
	
	@Input() user: User;

  constructor(
		private userService: UserService,
        private alertService: AlertService,
		private router: Router
  ) { }

	ngOnInit() {
	}
	
}
