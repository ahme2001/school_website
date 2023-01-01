import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizGradeComponent } from './quiz-grade.component';

describe('QuizGradeComponent', () => {
  let component: QuizGradeComponent;
  let fixture: ComponentFixture<QuizGradeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuizGradeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizGradeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
