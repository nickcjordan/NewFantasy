import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { UserCredential} from '../_models';
import { UserCredentialService} from '../_services';

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    currentUser: UserCredential
    users: UserCredential[] = [];

    constructor(private userService: UserCredentialService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllUsers();
    }

    deleteUser(username: string) {
        this.userService.delete(username).pipe(first()).subscribe(() => {
            this.loadAllUsers();
        });
    }

    private loadAllUsers() {
        this.userService.getAll().pipe(first()).subscribe(users => {
            this.users = users;
        });
    }
}