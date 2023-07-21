import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { ScIdService } from '../sc-id.service.service';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.css']
})
export class ServiceListComponent implements OnInit {
  services: any[] = [];
  serviceId: string | null | undefined;
  scId: string | null | undefined;

  constructor(private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private scIdService: ScIdService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.serviceId = params.get('serviceId');
      this.scId = this.scIdService.getScId(); // Get the scId from the service
      // ... fetchServiceDetails based on this.scId and this.serviceId ...
    });
    this.fetchAllServices();
  }

  fetchAllServices() {
    this.http.get<any[]>('http://localhost:8080/') // Endpoint to fetch all services from the backend
      .subscribe({
        next: (services) => {
          this.services = services;
          console.log(this.services);
        },
        error: (error) => {
          console.error('Error fetching services:', error);
          // Handle error: show error message, etc.
        }
      });
  }
  onSelectService(serviceId: string) {
    // Get the consumer ID from localStorage
   
    // Redirect to the selected service details page with the SCid and serviceId as route parameters
    // this.router.navigate([`${this.scId}/selectService/${serviceId}`]);
    console.log(this.scId);
    if (!this.scId) {
      console.error('Service consumer ID not available.');
      return;
    }

    // Send a GET request to the backend API endpoint to select the service
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
  }

