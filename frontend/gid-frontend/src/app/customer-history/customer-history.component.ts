import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoggedInServiceProviderService } from '../logged-in-service-provider-service.service';

@Component({
  selector: 'app-customer-history',
  templateUrl: './customer-history.component.html',
  styleUrls: ['./customer-history.component.css']
})
export class CustomerHistoryComponent implements OnInit {
  serviceConsumers: any[] = [];
  loggedInServiceProviderId: string | null = null;

  constructor(
    private http: HttpClient,
    private loggedInServiceProviderService: LoggedInServiceProviderService
  ) { }

  ngOnInit(): void {
    this.loggedInServiceProviderId = this.loggedInServiceProviderService.getLoggedInServiceProviderId();
    if (this.loggedInServiceProviderId) {
      this.fetchCustomerHistory();
    }
  }

  fetchCustomerHistory() {
    // Check if the user is logged in as a service provider
    if (!this.loggedInServiceProviderId) {
      console.error('Service provider ID not available.');
      return;
    }

    // Send a GET request to the backend API endpoint to fetch the customer history
    const apiUrl = `http://localhost:8080/serviceProvider/${this.loggedInServiceProviderId}/serviceConsumers`;
    this.http.get<any[]>(apiUrl)
      .subscribe({
        next: (response) => {
          console.log('Customer history fetched successfully:', response);
          this.serviceConsumers = response;
        },
        error: (error) => {
          console.error('Error fetching customer history:', error);
          // Handle error: show error message, etc.
        }
      });
  }
}
