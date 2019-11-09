import { Component, OnInit } from '@angular/core';
import {ChecklistService} from "../_services/checklist.service";
import {Router} from "@angular/router";
import {ChecklistItem} from "./checklistItem";
import {Checklist} from "./checklist";
import {faEdit, faPlusSquare, faTrashAlt} from '@fortawesome/free-regular-svg-icons';
import {faCheck} from "@fortawesome/free-solid-svg-icons";
import {DatePipe} from "@angular/common";

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

  constructor(private service: ChecklistService,
              private router: Router,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.loadChecklists();
  }

  loadChecklists(){
      return this.service.getChecklists(localStorage.getItem('current_user'), localStorage.getItem('current_household'), 'ASC').subscribe(
        data => {
          this.checklistsArray = data;
        },
        error => {
          if (error.toString() == "403") {
            this.router.navigate(['/login']);
          }
        })
  }

  getHousehold(): string {
    return localStorage.getItem("current_household");
  }

  openPopup() {
  }

  deleteChecklistItem(checklist: Checklist, item: ChecklistItem) {
      checklist.itemList = checklist.itemList.filter(i=>i!==item);
      this.service.saveChecklist(checklist).subscribe();

  }
  dateFromObjectId(objectId: string): string {
    return this.datePipe.transform(new Date(parseInt(objectId.substring(0, 8), 16) * 1000), 'yyyy-MM-dd HH:mm:ss');
  }

  changeEditMode(checklist: Checklist) {

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
    }  }
  deleteChecklist(checklist: Checklist) {
    if (checklist.creator == localStorage.getItem('current_user')){
      this.checklistsArray.filter()
    }
  }

  changeIsChecked(item: ChecklistItem) {
    item.isChecked=!item.isChecked;
  }
}
