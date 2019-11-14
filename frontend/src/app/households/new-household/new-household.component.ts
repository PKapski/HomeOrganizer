import {Component, OnInit} from '@angular/core';
import {Household} from "../household";
import {FormControl, NgForm, Validators} from "@angular/forms";
import {HouseholdService} from "../../_services/household.service";
import {UserService} from "../../_services/user.service";

@Component({
  selector: 'app-new-household',
  templateUrl: './new-household.component.html',
  styleUrls: ['./new-household.component.scss']
})
export class NewHouseholdComponent implements OnInit {

  household: Household = new Household();
  nameControl = new FormControl('', [Validators.required]);
  error: boolean;
  maxMessageLength: string = "300";
  descriptionControl = new FormControl('', [Validators.required]);


  constructor(private householdService: HouseholdService,
              private userService: UserService) {
  }

  ngOnInit() {
  }

  createHousehold() {
    if (this.nameControl.invalid && this.descriptionControl.invalid){
      return;
    }
    this.householdService.saveHousehold(this.household).subscribe(
      data=>{
        console.log(data);
        this.joinCreatedHousehold(data);
      },
      error=>{
        this.error=true;
      }
    )
  }

  joinCreatedHousehold(householdId: string){
    this.userService.setUserHousehold(localStorage.getItem('current_user'),householdId).subscribe(
      data=>{
        localStorage.setItem('current_household',householdId);
      },
      error => {
        this.error=true;
      }
    );
  }
  getErrorMessage() {
    return this.nameControl.hasError('required') ? 'You must enter a value' : '';
  }


}
