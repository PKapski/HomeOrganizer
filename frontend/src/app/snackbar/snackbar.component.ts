import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {MAT_SNACK_BAR_DATA, MatSnackBarRef} from "@angular/material/snack-bar";

@Component({
  selector: 'app-snackbar',
  templateUrl: './snackbar.component.html',
  styleUrls: ['./snackbar.component.scss']
})
export class SnackbarComponent implements OnInit {

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: string, private snackBarRef: MatSnackBarRef<SnackbarComponent>) { }

  ngOnInit() {
  }

  revert(){
    this.snackBarRef.dismissWithAction();
  }
}
