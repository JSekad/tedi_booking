import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { SelectionModel } from '@angular/cdk/collections';

import { UserService } from '../../services/user.service';
import { SnackBarService } from 'src/app/services/snackBar.service';

import { User } from '../../model/user.model';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  users: User[] = [];
  displayedColumns: string[] = ['username', 'eponymo', 'onoma', 'select'];
  dataSource = new MatTableDataSource<User>(this.users);
  selection = new SelectionModel<User>(true, []);

  constructor(private userService: UserService, private message: SnackBarService ){

    this.userService.usersMeAitimaEggrafis().subscribe({
      next: (response: User[]) => { this.users = response; },

      error:(error: HttpErrorResponse) => { this.message.error("Προέκυψε σφάλμα", 'Έξοδος'); }
    });
  }

  approveSelectedOwners(){
    this.selection.selected.forEach(u => {
      u.approved = true; 
      u.dateApproved = new Date();
    });

    for(let user of this.selection.selected){
      this.userService.updateUser(user).subscribe({
        error: (error: HttpErrorResponse) => {
          if(error.status != 200)
            this.message.error("Προέκυψε σφάλμα!", "Έξοδος");
        }
      });
    }
  }

  isAllSelected(){
    const numSelectedUsers = this.selection.selected.length;
    const totalUsers = this.dataSource.data.length;
    return numSelectedUsers === totalUsers;
  }

  selectAllUsers(){
    if(this.isAllSelected()){
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource.data);
  }

  checkboxLabel(row?: User, rowIndex?: number): string{
    if(!row){
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    if(rowIndex == null) rowIndex = 0;
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${rowIndex + 1}`;
  }

}