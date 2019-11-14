import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-households',
  templateUrl: './households.component.html',
  styleUrls: ['./households.component.scss']
})
export class HouseholdsComponent implements OnInit {

  selectedButton='new';

  constructor() { }

  ngOnInit() {

  }

  checkIfInHousehold(): boolean{
    return localStorage.getItem('current_household')!=null;
  }
}
