import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { ScIdService } from '../sc-id.service.service';
import { LoggedInServiceProviderService } from '../logged-in-service-provider-service.service';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.css']
})
export class ServiceListComponent implements OnInit {
  services: any[] = [];
  filteredServices: any[] = [];
  serviceId: string | null | undefined;
  scId: string | null | undefined;
  searchZipCode: string = '';

  constructor(private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private scIdService: ScIdService,
    ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.serviceId = params.get('serviceId');
      this.scId = this.scIdService.getScId(); // Get the scId from the service
      // ... fetchServiceDetails based on this.scId and this.serviceId ...
    });
    this.fetchAllServices();
  }

  fetchAllServices() {
    let apiUrl = 'http://localhost:8080/';
    if (this.searchZipCode) {
      apiUrl = `http://localhost:8080/fetchServiceByZipcode/${this.searchZipCode}`;
    }
    this.http.get<any[]>(apiUrl)
      .subscribe({
        next: (services) => {
          this.services = services;
          this.filteredServices = this.searchZipCode
            ? services.filter(service => service.zipCode === parseInt(this.searchZipCode, 10))
            : services;
          console.log(this.services);
        },
        error: (error) => {
          console.error('Error fetching services:', error);
          // Handle error: show error message, etc.
        }
      });
  }
  onSearchByZipCode() {
    // Trigger search when the Search button is clicked
    this.fetchAllServices();
  }
  onSelectService(serviceId: string) {
   
    console.log(this.scId);
    if (!this.scId) {
      console.error('Service consumer ID not available.');
      return;
    }

    const apiUrl = `http://localhost:8080/${this.scId}/selectService/${serviceId}`;
    this.http.get<any>(apiUrl)
      .subscribe({
        next: (response) => {
          console.log('Service selected successfully:', response);
          alert("Service selected");
          // Handle success: show success message, etc.
        },
        error: (error) => {
          console.error('Error selecting service:', error);
          // Handle error: show error message, etc.
        }
      });
  }
  goToOrderHistory() {
    this.router.navigate(['/order-history']);
  }
  }

