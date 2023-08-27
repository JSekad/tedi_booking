
import { Person } from './person.model';
import { Role } from './role';

export interface User{
	person: Person;
	username: String;
	role: Role;
}