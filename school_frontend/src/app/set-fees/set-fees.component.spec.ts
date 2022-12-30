import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetFeesComponent } from './set-fees.component';

describe('SetFeesComponent', () => {
  let component: SetFeesComponent;
  let fixture: ComponentFixture<SetFeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SetFeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SetFeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
