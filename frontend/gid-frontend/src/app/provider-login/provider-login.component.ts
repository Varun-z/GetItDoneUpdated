import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
  constructor(private http: HttpClient) { }
  loginProvider() {
    this.http.post<any>('http://localhost:8080/loginServiceProvider', this.provider)
      .subscribe({
        next: (response) => {
          console.log('provider logged in successfully:', response);
          // Handle success: show success message, redirect, etc.
        },
        error: (error) => {
          console.error('Error logging in provider:', error);
          // Handle error: show error message, etc.
        }
      });
  }
}
