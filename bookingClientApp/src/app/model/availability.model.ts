import { Room } from '../model/room.model';

export interface Availability{
	id: number | null;
	room: Room | null;
	startDate: Date;
	endDate: Date;
}