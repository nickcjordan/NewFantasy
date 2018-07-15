import { User } from "./user";
import {InMemoryDbService} from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
	createDb() {
		const users: User[] = [
			{id: 1, name: 'u1'},
			{id: 2, name: 'u2'},
			{id: 3, name: 'u3'},
			{id: 4, name: 'u4'},
			{id: 5, name: 'u5'},
			{id: 6, name: 'u6'}
		];
		return {users};
	}
}