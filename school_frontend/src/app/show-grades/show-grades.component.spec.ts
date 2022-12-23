import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowGradesComponent } from './show-grades.component';

describe('ShowGradesComponent', () => {
  let component: ShowGradesComponent;
  let fixture: ComponentFixture<ShowGradesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowGradesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowGradesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
