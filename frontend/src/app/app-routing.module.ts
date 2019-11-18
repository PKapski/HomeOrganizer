import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {NotesComponent} from "./notes/notes.component";
import {ChecklistsComponent} from "./checklists/checklists.component";
import {HouseholdsComponent} from "./households/households.component";
import {CalendarComponent} from "./calendar/calendar.component";
import {IndexComponent} from "./index/index.component";


const routes: Routes = [

  {path: '',component: IndexComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'notes', component: NotesComponent, runGuardsAndResolvers: 'always'},
  {path: 'checklists',component: ChecklistsComponent},
  {path: 'myhousehold',component: HouseholdsComponent},
  {path: 'calendar', component: CalendarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
