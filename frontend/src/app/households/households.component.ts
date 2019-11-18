import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-households',
  templateUrl: './households.component.html',
  styleUrls: ['./households.component.scss']
})
export class HouseholdsComponent implements OnInit {

  selectedButton='new';

  constructor(private router: Router) {
    if (localStorage.getItem('current_user')==null){
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {

  }

  checkIfInHousehold(): boolean{
    return localStorage.getItem('current_household')!=null;
  }
}
