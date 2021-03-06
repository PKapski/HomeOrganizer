  import {ChangeDetectionStrategy, Component, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView} from 'angular-calendar';
import {isSameDay, isSameMonth, startOfDay} from 'date-fns';
import {Subject} from "rxjs";
import {faArrowLeft, faArrowRight} from "@fortawesome/free-solid-svg-icons";
import {CalendarService} from "../_services/calendar.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {AuthService} from "../_services/auth.service";

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CalendarComponent implements OnInit {
  // viewDate: Date = new Date();
  arrowLeft = faArrowLeft;

  arrowRight = faArrowRight;

  view = CalendarView.Month;

  viewDate: Date = new Date();

  locale: string = 'en';

  viewDateChange: EventEmitter<Date> = new EventEmitter();

  events: CalendarEvent[];

  refresh: Subject<any> = new Subject();

  activeDayIsOpen: boolean = false;

  CalendarView = CalendarView;
  displayedColumns = ['color', 'title', 'startTime', 'endTime', 'allDay', 'apply', 'delete'];

  dataSource: MatTableDataSource<CalendarEvent>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private service: CalendarService,
              private router: Router) {

    if (localStorage.getItem('current_household')==null){
      this.router.navigate(['/myhousehold']);
    }
  }

  ngOnInit() {
    this.getCalendarEvents();
  }


  getCalendarEvents() {
    this.events = [];
    this.service.getCalendarEvents(localStorage.getItem('current_household')).subscribe(
      data => {
        data = data.map(x => {
          x.start = new Date(x.start);
          if (x.end != null) {
            x.end = new Date(x.end);
          }
          return x;
        });
        this.events = data;
        this.refreshContext();
      },
      error => {
        if (error.toString() == "403") {
          this.router.navigate(['/login']);
        }
      }
    )
  }

  refreshContext(){
    this.dataSource= new MatTableDataSource<CalendarEvent>(this.events);
    this.dataSource.paginator=this.paginator;
    this.refresh.next();
  }


  dayClicked({date, events}: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd
                    }: CalendarEventTimesChangedEvent): void {
    event.start = newStart;
    event.end = newEnd;
    this.service.saveCalendarEvent(event, localStorage.getItem('current_household')).subscribe(
      data => {
        this.refresh.next();
      },
      error => {
        if (error.toString() == "403") {
          AuthService.logout();
        }
      }
    );
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  editEvent(event: CalendarEvent) {
    this.service.saveCalendarEvent(event, localStorage.getItem('current_household')).subscribe(
      data => {
        let element = document.getElementById(event.id as string);
        (element as HTMLButtonElement).disabled = true;
        this.refresh.next();
      },
      error => {
        if (error.toString() == "403") {
          AuthService.logout();
        }
      }
    );
  }

  deleteEvent(event: CalendarEvent) {
    this.service.deleteCalendarEvent(event.id as string).subscribe(
      data => {
        this.events = this.events.filter(ev => ev !== event);
        this.refreshContext();
      },
      error => {
        if (error.toString() == "403") {
          AuthService.logout();
        }
      }
    );
  }

  addNewEvent() {
    let event = {} as CalendarEvent;
    event.title = "New event";
    event.start = startOfDay(new Date());
    event.resizable = {beforeStart: true, afterEnd: true};
    event.draggable = true;
    let color = this.getRandomColor();
    event.color = {primary: color, secondary: color};
    this.service.saveCalendarEvent(event, localStorage.getItem('current_household')).subscribe(
      data => {
        event.id = data;
        this.events = [event, ...this.events];
        this.refreshContext();
      },
      error => {
        if (error.toString() == "403") {
          AuthService.logout();
        }
      }
    )
  }

  getRandomColor() {
    var color = Math.floor(0x1000000 * Math.random()).toString(16);
    return '#' + ('000000' + color).slice(-6);
  }

  changeApplyIcon(id: string) {
    let element = document.getElementById(id);
    (element as HTMLButtonElement).disabled = false;
  }
}
