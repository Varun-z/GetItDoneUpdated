import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ScIdService } from '../sc-id.service.service';

@Component({
  selector: 'app-consumer-login',
  templateUrl: './consumer-login.component.html',
  styleUrls: ['./consumer-login.component.css']
})
export class ConsumerLoginComponent {
    consumer = {
      email: '',
      password: ''
    };
    constructor(private http: HttpClient, private router: Router, private scIdService: ScIdService ) { }
    loginConsumer() {
      this.http.post<any>('http://localhost:8080/loginServiceConsumer', this.consumer)
        .subscribe({
          next: (response) => {
            console.log('Consumer logged in successfully:', response);
            this.scIdService.setScId(response.id);
            this.router.navigate(['/service-list']);

            // Handle success: show success message, redirect, etc.
          },
          error: (error) => {
            console.error('Error logging in consumer:', error);
            // Handle error: show error message, etc.
          }
        });
    }
  }

