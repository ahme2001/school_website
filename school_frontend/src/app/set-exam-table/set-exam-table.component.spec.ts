import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetExamTableComponent } from './set-exam-table.component';

describe('SetExamTableComponent', () => {
  let component: SetExamTableComponent;
  let fixture: ComponentFixture<SetExamTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetExamTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetExamTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
