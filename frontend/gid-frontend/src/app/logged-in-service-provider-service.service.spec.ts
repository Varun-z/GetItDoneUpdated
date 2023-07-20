import { TestBed } from '@angular/core/testing';

import { LoggedInServiceProviderServiceService } from './logged-in-service-provider-service.service';

describe('LoggedInServiceProviderServiceService', () => {
  let service: LoggedInServiceProviderServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoggedInServiceProviderServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
