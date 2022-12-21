import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowExamTableComponent } from './show-exam-table.component';

describe('ShowExamTableComponent', () => {
  let component: ShowExamTableComponent;
  let fixture: ComponentFixture<ShowExamTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowExamTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowExamTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
