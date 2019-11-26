import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinHouseholdComponent } from './join-household.component';

describe('FindHouseholdComponent', () => {
  let component: JoinHouseholdComponent;
  let fixture: ComponentFixture<JoinHouseholdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinHouseholdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinHouseholdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
