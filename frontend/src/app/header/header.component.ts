import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import {AuthService} from "../_services/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {
  faHome = faHome;
  constructor(private auth: AuthService) { }

  ngOnInit() {
  }

}
