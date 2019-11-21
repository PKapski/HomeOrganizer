import {Component, OnInit} from '@angular/core';
import {ChecklistService} from "../_services/checklist.service";
import {Router} from "@angular/router";
import {ChecklistItem} from "./checklistItem";
import {Checklist} from "./checklist";
import {faEdit, faPlusSquare, faTrashAlt} from '@fortawesome/free-regular-svg-icons';
import {faCheck} from "@fortawesome/free-solid-svg-icons";
import {DatePipe} from "@angular/common";
import {AddChecklistDialogComponent} from "./add-checklist-dialog/add-checklist-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {SnackbarComponent} from "../snackbar/snackbar.component";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PageEvent} from "@angular/material/paginator";
import {HouseholdService} from "../_services/household.service";
import {AuthService} from "../_services/auth.service";

@Component({
  selector: 'app-checklists',
  templateUrl: './checklists.component.html',
  styleUrls: ['./checklists.component.scss'],
  providers: [DatePipe]
})


export class ChecklistsComponent implements OnInit {

  checklistsArray: any = [];
  currentlyEditedChecklist: string;
  addIcon = faPlusSquare;
  editIcon = faEdit;
  deleteIcon = faTrashAlt;
  applyIcon = faCheck;
  recentlyDeletedChecklist: Checklist;
  snackBarDuration = 5000;
  pageSize: number = 10;
  maxItems: number;
  pageEvent: PageEvent;
  firstResult: number = 0;
  pageSizeOptions = [5, 10,15,20];
  householdName: string;
  constructor(private service: ChecklistService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar,
              private router: Router,
              private datePipe: DatePipe,
              private householdService: HouseholdService) {
    if (localStorage.getItem('current_household')==null){
      this.router.navigate(['/myhousehold']);
    }
  }

  ngOnInit() {
    this.getPaginatedChecklists();
    this.getHouseholdName();
  }

  sort_by(field, ascending, primer) {
    var key = function (x) {
      return primer ? primer(x[field]) : x[field]
    };

    return function (a, b) {
      var A = key(a), B = key(b);
      return ((A < B) ? -1 : ((A > B) ? 1 : 0)) * [-1, 1][+!!ascending];
    }
  };

  getHouseholdName(){
    this.householdService.getHousehold(localStorage.getItem("current_household")).subscribe(
      data=>{
        this.householdName=data.name;
      },error => {
        if (error.toString() == "403") {
          AuthService.logout();
        }
      })
  }
  getPaginatedChecklists(event?: PageEvent) {
    if (event != null) {
      this.firstResult = event.pageIndex * event.pageSize;
      this.pageSize = event.pageSize;
    }
    this.service.getChecklists(localStorage.getItem('current_user'), localStorage.getItem('current_household'), this.firstResult, this.pageSize,"DESC").subscribe(
      data => {
        this.checklistsArray = data.array;
        this.maxItems=data.maxItems;
      },
      error => {
        if (error.toString() == "403") {
          this.router.navigate(['/login']);
        }
      });
    return event;
  }

  getHousehold(): string {
    return localStorage.getItem("current_household");
  }

  openPopup() {
    this.dialog.open(AddChecklistDialogComponent, {
      panelClass: 'my-panel',
      width: '600px',
      autoFocus: false
    }).afterClosed().subscribe(data => this.addNewChecklist(data));
  }

  addNewChecklist(data: any) {
    if (data == null) {
      return;
    }
    let checklist = data["data"] as Checklist;
    checklist.creator = localStorage.getItem("current_user");
    checklist.expirationDate = this.datePipe.transform(checklist.expirationDate, 'yyyy-MM-dd');
    checklist.householdId = localStorage.getItem("current_household");
    checklist.itemList = [];
    this.service.saveChecklist(checklist).subscribe(id => {
      checklist.id = id;
      this.checklistsArray.push(checklist);
      this.getPaginatedChecklists();
    });

  }

  deleteChecklistItem(checklist: Checklist, item: ChecklistItem) {
    checklist.itemList = checklist.itemList.filter(i => i !== item);
    this.service.saveChecklist(checklist).subscribe();
  }

  dateFromObjectId(objectId: string): string {
    return this.datePipe.transform(new Date(parseInt(objectId.substring(0, 8), 16) * 1000), 'yyyy-MM-dd HH:mm:ss');
  }

  changeItemEditMode(checklist: Checklist, item: ChecklistItem) {
    if ((checklist.creator != localStorage.getItem('current_user') && (checklist.recipent != localStorage.getItem('current_user')))) {
      return;
    }
    let itemHTMLElement = this.changeElementEditMode("item-" + checklist.id + "-" + checklist.itemList.indexOf(item));
    let menuIcon = document.getElementById("edit-" + checklist.id + "-" + checklist.itemList.indexOf(item));
    let applyIcon = document.getElementById("apply-" + checklist.id + "-" + checklist.itemList.indexOf(item));
    this.changeMenuIcon(itemHTMLElement.isContentEditable,menuIcon,applyIcon);
    if (!itemHTMLElement.isContentEditable){
      item.message=itemHTMLElement.innerText;
      if (item.message.trim() === ''){
        this.deleteChecklistItem(checklist,item);
      }else{
        this.service.saveChecklist(checklist).subscribe();
      }
    }
  }

  changeChecklistEditMode(checklist: Checklist) {
    if (checklist.creator != localStorage.getItem('current_user')) {
      return;
    }

    let checklistHTMLElement = this.changeElementEditMode("title-"+checklist.id);
    let editIcon = document.getElementById("edit-" + checklist.id);
    let applyIcon = document.getElementById("apply-" + checklist.id);
    this.changeMenuIcon(checklistHTMLElement.isContentEditable,editIcon,applyIcon);
    if (!checklistHTMLElement.isContentEditable){
      checklist.title=checklistHTMLElement.innerText;
      checklistHTMLElement.innerText=checklist.title;
      this.service.saveChecklist(checklist).subscribe();
    }
  }

  changeElementEditMode(id: string): HTMLElement {
    let element = document.getElementById(id);
    element.contentEditable = String(!element.isContentEditable);
    element.focus();
    return element;
  }

  changeMenuIcon(editMode:boolean, nonEditIcon: HTMLElement, editIcon: HTMLElement) {
    if(editMode){
      nonEditIcon.style.display="none";
      editIcon.style.display="inline-block";
    }else{
      nonEditIcon.style.display="inline-block";
      editIcon.style.display="none";
    }
  }

  getTooltipEditMessage(creator: string): string {
    if (creator == localStorage.getItem('current_user')) {
      return 'Edit the checklist'
    } else {
      return 'Only creator can edit a checklist!';
    }
  }

  getTooltipDeleteMessage(creator: string): string {
    if (creator == localStorage.getItem('current_user')) {
      return 'Delete the checklist'
    } else {
      return 'Only creator can delete a checklist!';
    }
  }

  deleteChecklist(checklist: Checklist) {
    if (checklist.creator == localStorage.getItem('current_user')) {
      this.recentlyDeletedChecklist = checklist;
      this.openSnackBar(checklist.title);
      this.checklistsArray = this.checklistsArray.filter(c => c !== checklist);
      this.service.deleteChecklist(checklist.id).subscribe(
        data=>{
          this.getPaginatedChecklists();
        },error => {
          if (error.toString() == "403") {
            AuthService.logout();
          }
        }
      );
    }
  }

  openSnackBar(title: string) {
    this.snackBar.openFromComponent(SnackbarComponent, {
      duration: this.snackBarDuration,
      data: {msg: "'" + title + "' note has been deleted!", reversible: true},
    }).onAction().subscribe(() => {
      this.reverseChecklistDeletion();
    });
  }

  reverseChecklistDeletion() {
    this.service.saveChecklist(this.recentlyDeletedChecklist).subscribe();
    this.checklistsArray.push(this.recentlyDeletedChecklist);
    this.checklistsArray.sort(this.sort_by("id", true, null));
  }

  changeIsChecked(checklist: Checklist, item: ChecklistItem) {
    item.isChecked = !item.isChecked;
    this.service.saveChecklist(checklist).subscribe();
  }

  checkIfDisabled(checklist: Checklist): boolean {
    let currDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    return currDate > checklist.expirationDate;
  }

  createNewChecklistItem(checklist: Checklist) {
    let checklistItem = new ChecklistItem();
    checklistItem.message=" ";
    checklistItem.isChecked=false;
    checklist.itemList.push(checklistItem);
    this.service.saveChecklist(checklist).subscribe(
      id => {
        this.changeItemEditMode(checklist,checklistItem);
      }
    );
  }



}
