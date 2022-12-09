import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetGradesComponent } from './set-grades.component';

describe('SetGradesComponent', () => {
  let component: SetGradesComponent;
  let fixture: ComponentFixture<SetGradesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetGradesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetGradesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
