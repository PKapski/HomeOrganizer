import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Household} from "../household";
import {HouseholdService} from "../../_services/household.service";
import {UserService} from "../../_services/user.service";
import {PageEvent} from "@angular/material/paginator";
import {faDoorOpen} from '@fortawesome/free-solid-svg-icons';
import {MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-my-household',
  templateUrl: './my-household.component.html',
  styleUrls: ['./my-household.component.scss'],
})
export class MyHouseholdComponent implements OnInit {

  pageEvent: PageEvent;
  displayedColumns: string[] = ['username', 'email', 'firstName'];
  myHousehold: Household;
  householdUsers: any = [];
  maxItems: number;
  pageSize: number = 5;
  firstResult: number = 0;
  sortedField: String;
  sortingDirection: String;
  exitIcon=faDoorOpen;

  constructor(private householdService: HouseholdService,
              private userService: UserService,
              private dialog: MatDialog,
              private router: Router) {
  }

  ngOnInit() {
    this.householdService.getHousehold(localStorage.getItem('current_household')).subscribe(
      data => {
        if (data != null) {
          this.myHousehold = data;

        }
      }
    );
    this.getHouseholdUsers();

  }

  getHouseholdUsers(event?: PageEvent) {
    if (event!=null) {
      this.firstResult = event.pageIndex * event.pageSize;
    }
    this.userService.getHouseholdUsers(localStorage.getItem('current_household'),this.firstResult,this.pageSize).subscribe(
      data => {
        this.householdUsers = data.array;
        this.maxItems = data.maxItems;
      }
    );
    return event;
  }

  openConfirmationDialog() {
    this.dialog.open(ConfirmationDialogComponent,{
      width:'400px'
    }).afterClosed().subscribe(
      (result: boolean)=>{
        if (result){
          this.leaveHousehold();
        }
      }
    )
  }

  private leaveHousehold() {
    localStorage.removeItem('current_household');
    this.userService.setUserHousehold(localStorage.getItem('current_user'),null).subscribe();
    this.router.navigate(['/myhousehold']);
  }
}
