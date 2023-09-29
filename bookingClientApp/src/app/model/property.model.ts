import { City } from './city.model';
import { Person } from './person.model';

export interface Property{
	id: number;
	owner: Person;
	description: string;
	address: string;
	addressNumber: string;
	accessInformation: string;
	rating: number;
	city: City;
	longitude: number;
	latitude: number;
}