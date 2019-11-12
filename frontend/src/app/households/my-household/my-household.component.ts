import { Component, OnInit } from '@angular/core';
import {Household} from "../household";
import {HouseholdService} from "../../_services/household.service";
import {UserService} from "../../_services/user.service";

@Component({
  selector: 'app-my-household',
  templateUrl: './my-household.component.html',
  styleUrls: ['./my-household.component.scss']
})
export class MyHouseholdComponent implements OnInit {

  displayedColumns: string[] = ['username','email', 'firstName'];
  myHousehold: Household;
  householdUsers: any=[];

  constructor(private householdService: HouseholdService,
              private userService: UserService) { }

  ngOnInit() {
    this.householdService.getHousehold(localStorage.getItem('current_household')).subscribe(
      data=>{
        if (data!=null){
          this.myHousehold=data;
        }
      }
    );
    this.userService.getHouseholdUsers(localStorage.getItem('current_household')).subscribe(
      data=>{
        this.householdUsers=data;
      }
    )
  }

}
