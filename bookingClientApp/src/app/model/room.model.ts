export interface Room {
	id: number;
	basePricePerNight: number;
	description: string;
	numOfDoubleBeds: number;
	numOfSingleBeds:number;
	numOfBedRooms: number;
	numOfBathrooms: number;
	hasTV: boolean;
	hasPrivateBathroom: boolean;
	hasAirCondition: boolean;
	hasWifi: boolean;
	hasKitechen: boolean;
	hasJacuzzi: boolean;
	areaSize: number;
	capacity: number;
	petsAllowed: boolean;
	hasParking: boolean;
	hasEleveator: boolean;
}
