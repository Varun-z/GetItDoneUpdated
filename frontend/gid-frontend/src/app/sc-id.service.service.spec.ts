import { TestBed } from '@angular/core/testing';

import { ScIdServiceService } from './sc-id.service.service';

describe('ScIdServiceService', () => {
  let service: ScIdServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScIdServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
