import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {UserService} from "../../_services/user.service";
import {ChecklistService} from "../../_services/checklist.service";

@Component({
  selector: 'app-add-checklist-dialog',
  templateUrl: './add-checklist-dialog.component.html',
  styleUrls: ['./add-checklist-dialog.component.scss']
})
export class AddChecklistDialogComponent implements OnInit {
  usersList: string[];
  formGroup: FormGroup;
  constructor(private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<AddChecklistDialogComponent>,
              private usersService: UserService) { }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
    title: ['', [Validators.required]],
    recipent: [],
    visibleToEveryone: [true],
    expirationDate: []
  });

    this.usersService.getHouseholdUsers(localStorage.getItem("current_household")).subscribe(
      data => {
        this.usersList=data.array.map(user=>user.username).filter(name=>name!=localStorage.getItem("current_user"));
      });
  }

  get title() {
    return this.formGroup.get('title');
  }

  get message() {
    return this.formGroup.get('message');
  }

  get recipent(){
    return this.formGroup.get('recipent');
  }
  onCancel(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.formGroup.valid) {
      this.dialogRef.close({data: this.formGroup.value});
    }
    console.log(this.recipent);
  }
}
