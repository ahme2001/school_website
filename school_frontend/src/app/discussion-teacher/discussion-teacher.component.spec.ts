import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscussionTeacherComponent } from './discussion-teacher.component';

describe('DiscussionTeacherComponent', () => {
  let component: DiscussionTeacherComponent;
  let fixture: ComponentFixture<DiscussionTeacherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiscussionTeacherComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiscussionTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
