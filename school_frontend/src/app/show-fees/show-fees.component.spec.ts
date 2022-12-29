import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowFeesComponent } from './show-fees.component';

describe('ShowFeesComponent', () => {
  let component: ShowFeesComponent;
  let fixture: ComponentFixture<ShowFeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowFeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowFeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
