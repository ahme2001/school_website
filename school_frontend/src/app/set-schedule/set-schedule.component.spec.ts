import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetScheduleComponent } from './set-schedule.component';

describe('SetScheduleComponent', () => {
  let component: SetScheduleComponent;
  let fixture: ComponentFixture<SetScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetScheduleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
