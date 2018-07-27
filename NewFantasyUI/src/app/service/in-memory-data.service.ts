import { User } from "../model/user";
import {InMemoryDbService} from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
	createDb() {
		const users: User[] = [
//			{id: "1", userName: 'u1', coins: 1},
//			{id: "2", userName: 'u2', coins: 2},
//			{id: "3", userName: 'u3', coins: 3},
//			{id: "4", userName: 'u4', coins: 4},
//			{id: "5", userName: 'u5', coins: 5},
//			{id: "6", userName: 'u6', coins: 6}
		];
		return {users};
	}
}