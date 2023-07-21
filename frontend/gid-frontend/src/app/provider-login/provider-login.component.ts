import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoggedInServiceProviderService } from '../logged-in-service-provider-service.service';

@Component({
  selector: 'app-provider-login',
  templateUrl: './provider-login.component.html',
  styleUrls: ['./provider-login.component.css']
})
export class ProviderLoginComponent {
  provider = {
    email: '',
    password: ''
  };
  constructor(
    private http: HttpClient,
    private router: Router,
    private loggedInServiceProviderService: LoggedInServiceProviderService // Inject the LoggedInServiceProviderService
  ) { }

  loginProvider() {
    this.http.post<any>('http://localhost:8080/loginServiceProvider', this.provider)
      .subscribe({
        next: (response) => {
          console.log('provider logged in successfully:', response);
          const loggedInServiceProviderId = response.id;
          // Get the actual service provider's ID from the response
          this.loggedInServiceProviderService.setLoggedInServiceProviderId(loggedInServiceProviderId);
          console.log(this.loggedInServiceProviderService.getLoggedInServiceProviderId());
          this.router.navigate(['/post-service', loggedInServiceProviderId]);// Navigate to PostServiceComponent
        },
        error: (error) => {
          console.error('Error logging in provider:', error);
          // Handle error: show error message, etc.
        }
      });
  }
}
