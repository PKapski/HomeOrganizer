import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FindHouseholdComponent } from './find-household.component';

describe('FindHouseholdComponent', () => {
  let component: FindHouseholdComponent;
  let fixture: ComponentFixture<FindHouseholdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FindHouseholdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FindHouseholdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
