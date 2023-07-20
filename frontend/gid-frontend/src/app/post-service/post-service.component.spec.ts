import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostServiceComponent } from './post-service.component';

describe('PostServiceComponent', () => {
  let component: PostServiceComponent;
  let fixture: ComponentFixture<PostServiceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostServiceComponent]
    });
    fixture = TestBed.createComponent(PostServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
