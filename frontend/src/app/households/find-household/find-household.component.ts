import { Component, OnInit } from '@angular/core';
import {UserService} from "../../_services/user.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-find-household',
  templateUrl: './find-household.component.html',
  styleUrls: ['./find-household.component.scss']
})
export class FindHouseholdComponent implements OnInit {
  householdId: string;
  idControl = new FormControl('', [Validators.required]);
  error: boolean;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  joinHousehold() {
    let household = this.householdId;
    if (this.householdId==null){
      return;
    }
      this.userService.setUserHousehold(localStorage.getItem('current_user'),this.householdId).subscribe(
        data=>{
          localStorage.setItem('current_household',household);
        },
        error => {
          this.error=true;
        }
      );
  }

  getErrorMessage() {
    return this.idControl.hasError('required') ? 'You must enter a value' : '';
  }
}
