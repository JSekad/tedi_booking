import { Person } from './person.model';
import { Room } from './room.model';

export interface Reservation{
	id: number | null | undefined;
	room: Room;
	guest: Person;	
	numOfPersons: number;
	price: number;
	reservationTimestamp: Date;
	cancelationTimestamp: Date | null;
	startDate: Date;
	endDate: Date;
}