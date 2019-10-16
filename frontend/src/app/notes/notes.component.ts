import { Component, OnInit } from '@angular/core';
import {NotesService} from "./notes.service";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {
  notesList: any=[];

  constructor(public notesService: NotesService) { }

  ngOnInit() {
    this.loadNotes();
  }

  loadNotes(){
    return this.notesService.GetNotes().subscribe((data: {})=>{
      this.notesList=data;
    })
  }

}
