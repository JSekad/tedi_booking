import { Person } from './person.model';
import { Room } from './room.model';

export class Reservation{
	id: number | undefined;
	room: Room;
	guest: Person;	
	numOfPersons: number;
	price: number;
	reservationTimestamp: Date;
	cancelationTimestamp: Date | null;
	startDate: Date;
	endDate: Date;

	constructor(room: Room, guest: Person, numOfPersons: number, price: number, reservationTimestamp: Date,
	  			cancelationTimestamp: Date | null, startDate: Date, endDate: Date){
		this.room = room;
		this.guest = guest;
		this.numOfPersons = numOfPersons;
		this.price = price;
		this.reservationTimestamp = reservationTimestamp;
		this.cancelationTimestamp = cancelationTimestamp;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}