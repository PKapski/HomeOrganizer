import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {Note} from "../note";

@Component({
  selector: 'app-add-note-dialog',
  templateUrl: './add-note-dialog.component.html',
  styleUrls: ['./add-note-dialog.component.scss']
})
export class AddNoteDialogComponent implements OnInit {
  usersList=["abs","bcd","cds", "Pati"];

  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              public dialogRef: MatDialogRef<AddNoteDialogComponent>) {
  }

  ngOnInit() {
    this.formGroup=this.formBuilder.group({
      title: ['', [Validators.required]],
      recipent: [],
      expirationDate: [],
      message: ['',[Validators.required]]
    });
  }

  get title() {return this.formGroup.get('title');}
  get message() {return this.formGroup.get('message');}

  onCancel(): void {
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.formGroup.invalid){
      return;
    }
    this.dialogRef.close({data: this.formGroup.value});
  }
}
