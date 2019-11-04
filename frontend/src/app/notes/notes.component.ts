import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {NotesService} from "../_services/notes.service";
import {Note} from "./note";
import {faEdit, faPlusSquare, faTrashAlt} from '@fortawesome/free-regular-svg-icons';
import {faCheck} from "@fortawesome/free-solid-svg-icons";
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackbarComponent} from "../snackbar/snackbar.component";
import {MatDialog} from "@angular/material/dialog";
import {AddNoteDialogComponent} from "./add-note-dialog/add-note-dialog.component";
import {log} from "util";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss'],
  providers: [DatePipe]
})
export class NotesComponent implements OnInit {
  notesList: any = [];
  editMode = false;
  messageHTMLElement: HTMLElement;
  titleHTMLElement: HTMLElement;
  editIcon = faEdit;
  deleteIcon = faTrashAlt;
  addIcon = faPlusSquare;
  applyIcon = faCheck;
  currentlyEditedNote: string;
  snackBarDuration = 5000;//in seconds
  recentlyDeletedNote: Note;
  testNote: Note;

  @ViewChild('text-block', {static: false}) textBox: ElementRef;

  constructor(public notesService: NotesService,
              private snackBar: MatSnackBar,
              public dialog: MatDialog,
              private datePipe: DatePipe) {
  }

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes() {
    return this.notesService.getNotes().subscribe((data: {}) => {
      this.notesList = data;
    })
  }


  changeEditMode(note: Note) {
    this.editMode = !this.editMode;
    this.messageHTMLElement = this.changeElementEditMode("msg-" + note.id);
    this.titleHTMLElement = this.changeElementEditMode("title-" + note.id);
    if (this.editMode) {
      this.currentlyEditedNote = note.id;
    } else {
      note.title = this.titleHTMLElement.innerText;
      note.message = this.messageHTMLElement.innerText;
      this.notesService.postNote(note).subscribe();
      this.currentlyEditedNote = null;
    }
  }

  changeElementEditMode(id: string): HTMLElement {
    let element;
    element = document.getElementById(id);
    element.contentEditable = String(this.editMode);
    element.focus();
    return element;
  }

  deleteNote(note: Note) {
    this.notesList = this.notesList.filter(n => n !== note);
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
    console.log(this.recentlyDeletedNote);
    this.notesService.postNote(this.recentlyDeletedNote).subscribe();
    this.notesList.push(this.recentlyDeletedNote);
  }


  onPaste(event: ClipboardEvent, note: Note) {
    event.preventDefault();
    const text = event.clipboardData.getData('text/plain');
    document.getElementById("msg-" + note.id).innerText += text;
  }

  openPopup() {
    this.dialog.open(AddNoteDialogComponent, {panelClass: 'my-panel', width: '600px'}).afterClosed().subscribe(data=>this.addNewNote(data));
  }

  addNewNote(data: any){
    if (data==null){
      return;
    }
    let note = data["data"] as Note;
    note.creator="Me"; //#FIXME
    note.creationDate=this.datePipe.transform(new Date(),'yyyy-MM-dd');
    console.log(note.id);
    this.notesService.postNote(note).subscribe(id=>note.id=id);
    this.notesList.push(note);
  }
}
