import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-households',
  templateUrl: './households.component.html',
  styleUrls: ['./households.component.scss']
})
export class HouseholdsComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  checkIfInHousehold(){
    return localStorage.getItem('current_household')!=null;
  }
}
