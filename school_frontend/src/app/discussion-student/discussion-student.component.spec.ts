import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscussionStudentComponent } from './discussion-student.component';

describe('DiscussionStudentComponent', () => {
  let component: DiscussionStudentComponent;
  let fixture: ComponentFixture<DiscussionStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiscussionStudentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiscussionStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
