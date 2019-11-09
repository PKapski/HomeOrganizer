import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {faAngleDown, faHome, faSignInAlt, faTasks, faUser, faUserPlus} from '@fortawesome/free-solid-svg-icons';
import {faCalendarAlt, faStickyNote} from "@fortawesome/free-regular-svg-icons";
import {AuthService} from "../_services/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {
  faHome = faHome;
  faAngleDown = faAngleDown;
  faNote = faStickyNote;
  faChecklist = faTasks;
  faCalendar = faCalendarAlt;
  faUser = faUser;
  faLogin = faSignInAlt;
  faRegister = faUserPlus;

  constructor(private auth: AuthService) {
  }

  ngOnInit() {
  }

  getUsername(): string {
    return localStorage.getItem("current_user");
  }
}
