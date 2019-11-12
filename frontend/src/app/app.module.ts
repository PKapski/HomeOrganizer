import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NotesComponent } from './notes/notes.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NotesService} from "./_services/notes.service";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import { RegisterComponent } from './register/register.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import { SnackbarComponent } from './snackbar/snackbar.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { AddNoteDialogComponent } from './notes/add-note-dialog/add-note-dialog.component';
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatDialogModule} from "@angular/material/dialog";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {AuthInterceptor} from "./interceptor/auth-interceptor";
import {MatMenuModule} from "@angular/material/menu";
import {MatCheckboxModule} from "@angular/material/checkbox";
import { ChecklistsComponent } from './checklists/checklists.component';
import {MatIconModule} from "@angular/material/icon";
import {ChecklistService} from "./_services/checklist.service";
import { AddChecklistDialogComponent } from './checklists/add-checklist-dialog/add-checklist-dialog.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import { HouseholdsComponent } from './households/households.component';
import { FindHouseholdComponent } from './households/find-household/find-household.component';
import { NewHouseholdComponent } from './households/new-household/new-household.component';
import { MyHouseholdComponent } from './households/my-household/my-household.component';
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NotesComponent,
    LoginComponent,
    RegisterComponent,
    SnackbarComponent,
    AddNoteDialogComponent,
    ChecklistsComponent,
    AddChecklistDialogComponent,
    HouseholdsComponent,
    FindHouseholdComponent,
    NewHouseholdComponent,
    MyHouseholdComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatDialogModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMenuModule,
    MatCheckboxModule,
    MatIconModule,
    MatPaginatorModule,
    MatCardModule,
    MatTableModule
  ],
  entryComponents:[
    SnackbarComponent,
    AddNoteDialogComponent,
    AddChecklistDialogComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    NotesService,
    ChecklistService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
