import {Person} from "./person.model";
import {Role} from "./role";

export interface User {
  person: Person;
  roles: [Role];
  enabled: boolean;
  accountNonLocked: boolean;
  accountNonExpired: boolean;
  credentialsNonExpired: boolean;
  authorities: [
    {
      authority: string
    }
  ];
  username: string
}
