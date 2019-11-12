import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {NotesService} from "../_services/notes.service";
import {Note} from "./note";
import {faEdit, faPlusSquare, faTrashAlt} from '@fortawesome/free-regular-svg-icons';
import {faCheck} from "@fortawesome/free-solid-svg-icons";
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackbarComponent} from "../snackbar/snackbar.component";
import {MatDialog} from "@angular/material/dialog";
import {AddNoteDialogComponent} from "./add-note-dialog/add-note-dialog.component";
import {DatePipe} from '@angular/common';
import {Router} from "@angular/router";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss'],
  providers: [DatePipe]
})
export class NotesComponent implements OnInit {
  notesArray: any = [];
  editIcon = faEdit;
  deleteIcon = faTrashAlt;
  addIcon = faPlusSquare;
  applyIcon = faCheck;
  snackBarDuration = 5000;//in seconds
  recentlyDeletedNote: Note;

  @ViewChild('text-block', {static: false}) textBox: ElementRef;

  constructor(public notesService: NotesService,
              private snackBar: MatSnackBar,
              public dialog: MatDialog,
              private datePipe: DatePipe,
              private router: Router) {
  }

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes() {
    return this.notesService.getNotes(localStorage.getItem('current_user'), localStorage.getItem('current_household'), 'ASC').subscribe(
      data => {
        this.notesArray = data;
      },
      error => {
        if (error.toString() == "403") {
          this.router.navigate(['/login']);
        }
      })
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



  changeEditMode(note: Note) {
    if (note.creator != localStorage.getItem('current_user')) {
      return;
    }
    let messageHTMLElement = this.changeElementEditMode("msg-" + note.id);
    let titleHTMLElement = this.changeElementEditMode("title-" + note.id);
    let editIcon = document.getElementById("edit-" + note.id);
    let applyIcon = document.getElementById("apply-" + note.id)
    this.changeMenuIcon(messageHTMLElement.isContentEditable,editIcon,applyIcon);
    if (!messageHTMLElement.isContentEditable) {
      note.title = titleHTMLElement.innerText;
      titleHTMLElement.innerText=note.title;
      note.message = messageHTMLElement.innerText;
      messageHTMLElement.innerText=note.message;
      this.notesService.postNote(note).subscribe();
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
      editIcon.style.display="initial";
    }else{
      nonEditIcon.style.display="initial";
      editIcon.style.display="none";
    }
  }

  deleteNote(note: Note) {
    if (note.creator != localStorage.getItem('current_user')) {
      return;
    }
    this.openSnackBar(note.title);
    this.notesArray = this.notesArray.filter(n => n !== note);
    this.notesService.deleteNote(note.id).subscribe();
    this.recentlyDeletedNote = note;
  }

  openSnackBar(title: string) {
    this.snackBar.openFromComponent(SnackbarComponent, {
      duration: this.snackBarDuration,
      data: "'" + title + "' note has been deleted!",
    }).onAction().subscribe(() => {
      this.reverseNoteDeletion();
    });
  }

  reverseNoteDeletion() {
    this.notesService.postNote(this.recentlyDeletedNote).subscribe();
    this.notesArray.push(this.recentlyDeletedNote);
    this.notesArray.sort(this.sort_by("id", true, null));
  }


  onPaste(event: ClipboardEvent, note: Note) {
    event.preventDefault();
    const text = event.clipboardData.getData('text/plain');
    document.getElementById("msg-" + note.id).innerText += text;
  }

  openPopup() {
    this.dialog.open(AddNoteDialogComponent, {
      panelClass: 'my-panel',
      width: '600px',
      autoFocus: false
    }).afterClosed().subscribe(data => this.addNewNote(data));
  }

  addNewNote(data: any) {
    if (data == null) {
      return;
    }
    let note = data["data"] as Note;
    note.creator = localStorage.getItem("current_user");
    note.expirationDate = this.datePipe.transform(note.expirationDate, 'yyyy-MM-dd');
    note.householdId=localStorage.getItem("current_household");
    this.notesService.postNote(note).subscribe(id => {
      note.id = id;
      this.notesArray.push(note);
    });

  }

  dateFromObjectId(objectId: string): string {
    return this.datePipe.transform(new Date(parseInt(objectId.substring(0, 8), 16) * 1000), 'yyyy-MM-dd HH:mm:ss');
  }

  getHousehold(): string {
    return localStorage.getItem("current_household");
  }

  getTooltipEditMessage(creator: string): string {
    if (creator == localStorage.getItem('current_user')) {
      return 'Edit the note'
    } else {
      return 'Only creator can edit a note!';
    }
  }

  getTooltipDeleteMessage(creator: string): string {
    if (creator == localStorage.getItem('current_user')) {
      return 'Delete the note'
    } else {
      return 'Only creator can delete a note!';
    }  }

  checkIfDisabled(note: Note): boolean {
    let currDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    return currDate> note.expirationDate;
  }



}
