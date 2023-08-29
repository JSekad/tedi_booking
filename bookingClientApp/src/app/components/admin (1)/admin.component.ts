import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

import { UserService } from '../../services/user.service';
import { SnackBarService } from 'src/app/services/snackBar.service';

import { User } from '../../model/user.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  users: User[] = [];
  columns = [
    {
      columnDef: 'position',
      header: 'Α/Α',
      cell: (user: User) => `${this.users.indexOf(user)}`
    },
    {
      columnDef: 'Username',
      header: 'Username',
      cell: (user: User) => `${user.username}`
    },
    {
      columnDef: 'Surnname',
      header: 'Επώνυμο',
      cell: (user: User) => `${user.person.surname}`
    },
    {
      columnDef: 'Name',
      header: 'Όνομα',
      cell: (user: User) => `${user.person.name}`
    }
  ]

  displayedColumns = this.columns.map(c => c.columnDef);

  constructor(private userService: UserService, private message: SnackBarService ){

    this.userService.usersMeAitimaEggrafis().subscribe({
      next: (response: User[]) => { this.users = response; },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });
  }

}
