import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {UserService} from "../../_services/user.service";
import {User} from "../../user/user";


@Component({
  selector: 'app-add-note-dialog',
  templateUrl: './add-note-dialog.component.html',
  styleUrls: ['./add-note-dialog.component.scss']
})
export class AddNoteDialogComponent implements OnInit {
  usersList = [];
  maxMessageLength="250";
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<AddNoteDialogComponent>,
              private usersService: UserService) {
  }

  ngOnInit() {
    this.formGroup = this.formBuilder.group({
      title: ['', [Validators.required]],
      recipent: [],
      visibleToEveryone: [true],
      expirationDate: [],
      message: ['', [Validators.required]],
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
    console.log(this.formGroup.value);
    if (this.formGroup.valid) {
      this.dialogRef.close({data: this.formGroup.value});
    }
    console.log(this.recipent);
  }
}
