import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoggedInServiceProviderService {
  private loggedInServiceProviderId: string | null = null;

  setLoggedInServiceProviderId(id: string): void {
    this.loggedInServiceProviderId = id;
  }

  getLoggedInServiceProviderId(): string | null {
    return this.loggedInServiceProviderId;
  }
}