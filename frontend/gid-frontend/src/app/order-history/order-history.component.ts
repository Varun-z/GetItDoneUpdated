import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoggedInServiceProviderService } from '../logged-in-service-provider-service.service';
import { ScIdService } from '../sc-id.service.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  serviceConsumerId: string | null | undefined;
  orderedServices: any[] = [];

  constructor(private http: HttpClient, private scIdService: ScIdService) { }

  ngOnInit(): void {
    // Fetch the service consumer ID from wherever you stored it (e.g., localStorage)
    this.serviceConsumerId = this.scIdService.getScId();

    // Fetch the list of ordered services for the service consumer from the backend
    if (this.serviceConsumerId) {
      const apiUrl = `http://localhost:8080/fetchServiceConsumer/${this.serviceConsumerId}`;
      this.http.get<any>(apiUrl)
        .subscribe({
          next: (serviceConsumer) => {
            this.orderedServices = serviceConsumer.servicesList;
            console.log(this.orderedServices);
          },
          error: (error) => {
            console.error('Error fetching ordered services:', error);
            // Handle error: show error message, etc.
          }
        });
    }
  }
}
