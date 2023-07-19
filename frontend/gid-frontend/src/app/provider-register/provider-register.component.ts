import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-provider-register',
  templateUrl: './provider-register.component.html',
  styleUrls: ['./provider-register.component.css']
})
export class ProviderRegisterComponent {
  provider = {
    name: '',
    email: '',
    password: ''
  };
  constructor(private http: HttpClient) { }
  registerProvider() {
    this.http.post<any>('http://localhost:8080/registerServiceProvider', this.provider)
      .subscribe({
        next: (response) => {
          console.log('Provider registered successfully:', response);
          // Handle success: show success message, redirect, etc.
        },
        error: (error) => {
          console.error('Error registering provider:', error);
          // Handle error: show error message, etc.
        }
      });
  }}


