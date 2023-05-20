import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

import { SearchService } from '../../services/search.service';
import { Room } from './../../model/room.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  area: string | null = null;
  startDate: Date | null = null;
  endDate: Date | null = null;
  numPersons: number = 1;
  rooms: Room[] = []; 

  constructor(
    private searchService: SearchService,
    private formBuilder: FormBuilder,
  ){ }

  searchRooms(){

    if(this.area === null || this.area.length == 0){
      alert('Εισάγετε τοποθεσία');
      return;
    }
    if(this.startDate == null || this.endDate == null){
      alert('Εισάγετε ημερομηνία από και εως');
      return;
    }  
    if(this.numPersons < 1){
      alert('Εισάγετε αριθμό ατόμων.');
      return;
    }

     this.searchService.getRooms(this.area, this.startDate, this.endDate, this.numPersons) .subscribe({
      next: ( response: Room[]) => { this.rooms = response; },

      error:(error: HttpErrorResponse) => { alert(error.message); }

     });
  }
  
}
