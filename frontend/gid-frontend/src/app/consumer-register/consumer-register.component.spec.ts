import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerRegisterComponent } from './consumer-register.component';

describe('ConsumerRegisterComponent', () => {
  let component: ConsumerRegisterComponent;
  let fixture: ComponentFixture<ConsumerRegisterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsumerRegisterComponent]
    });
    fixture = TestBed.createComponent(ConsumerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
