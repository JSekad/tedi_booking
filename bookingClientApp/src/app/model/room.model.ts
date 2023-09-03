import { RoomImageDefault } from './roomImageDefault.model';
import { RoomType} from './room-type.model';
import { Property } from './property.model';

export interface Room {
	id: number;
	property: Property;
	type: RoomType;
	basePricePerNight: number;
	description: string;
	defaultRoomImage: RoomImageDefault | null;
	numOfDoubleBeds: number;
	numOfSingleBeds:number;
	numOfBedrooms: number;
	numOfBathrooms: number;
	minRentDays: number;
	hasTV: boolean;
	hasPrivateBathroom: boolean;
	hasAirCondition: boolean;
	hasWifi: boolean;
	hasKitchen: boolean;
	hasJacuzzi: boolean;
	areaSize: number;
	capacity: number;
	petsAllowed: boolean;
	hasParking: boolean;
	hasElevator: boolean;
	smokingAllowed: boolean;
	partyAllowed: boolean;
	numOfReviews: number;
	averageReviews: number;
}