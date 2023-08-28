import {Person} from "./person.model";
import {Role} from "./role";

export interface User {
  person: Person;
  username: string
  roles: [Role];
  enabled: boolean;
  accountNonLocked: boolean;
  accountNonExpired: boolean;
  credentialsNonExpired: boolean;
  approved: boolean;
  dateApproved: Date;
  authorities: [
    {
      authority: string
    }
  ];
}
