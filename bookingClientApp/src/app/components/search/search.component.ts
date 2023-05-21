import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { formatDate } from '@angular/common';

import { SearchService } from '../../services/search.service';
import { Room } from './../../model/room.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  area: string | null = null;
  startDate: string | null = null;
  endDate: string | null = null ;
  today: Date = new Date();
  numPersons: number = 1;
  rooms: Room[] = []; 

  constructor(
    private searchService: SearchService,
    private formBuilder: FormBuilder,
  ){ }

  searchRooms(){

     this.searchInputValidation();

     this.searchService.getRooms(this.area, this.startDate, this.endDate, this.numPersons) .subscribe({
      next: ( response: Room[]) => { this.rooms = response; },

      error:(error: HttpErrorResponse) => { alert(error.message); }
     });
  }

  searchInputValidation(){

    if(this.area === null || this.area.length === 0){
      alert('Εισάγετε τοποθεσία');
      return;
    }

    if(this.startDate === null || this.endDate === null){
      alert('Εισάγετε ημερομηνία από και εως');
      return;
    }  

    if(this.startDate > this.endDate){
      alert('Η ημερομηνία από πρέπει να είναι μεταγενέστερη της ημερομηνίας εως');
      return;
    }

    if(this.startDate < this.today.toISOString().split('T')[0]){
        alert('Η ημερομηνία από, πρέπει να είναι μεγαλύτερη ή ίση της σημερινής.')
        return;
    }

    if(this.numPersons < 1){
      alert('Εισάγετε αριθμό ατόμων.');
      return;
    }
  
  }
  
}
