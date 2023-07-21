import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

// interface ServiceConsumer {
//   name: string;
//   email: string;
//   password: string;
// }

@Component({
  selector: 'app-consumer-register',
  templateUrl: './consumer-register.component.html',
  styleUrls: ['./consumer-register.component.css']
})

export class ConsumerRegisterComponent {
  consumer = {
    name: '',
    email: '',
    password: ''
  };
  constructor(private http: HttpClient, private router: Router) { }

  registerConsumer() {
    this.http.post<any>('http://localhost:8080/registerServiceConsumer', this.consumer)
      .subscribe({
        next: (response) => {
          console.log('Consumer registered successfully:', response);
          this.router.navigate(['/login/consumer']);
          // Handle success: show success message, redirect, etc.
        },
        error: (error) => {
          console.error('Error registering consumer:', error);
          // Handle error: show error message, etc.
        }
      });
  }}
