import {Person} from "./person.model";
import {Role} from "./role";

export interface User {
  id: number;
  surname: string;
  name: string;
  birthDate: string;
  idNumber: string;
  email: string;
  phoneNumber: string;
  username: string
  roles: [Role];
  enabled: boolean;
  accountNonLocked: boolean;
  accountNonExpired: boolean;
  credentialsNonExpired: boolean;
  approved: boolean;
  dateApproved: Date;
  numOfReviews: number;
  averageReviews: number;
  authorities: [
    {
      authority: string
    }
  ];
}
