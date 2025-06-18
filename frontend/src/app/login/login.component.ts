import { Component } from '@angular/core';
import { Router } from '@angular/router'; 
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {} 

  login(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        const token = response.token;
        console.log('Token recibido:', token);
        localStorage.setItem('token', token);
        this.router.navigate(['/lists']); 
      },
      error: () => {
        this.errorMessage = 'Credenciales incorrectas';
      }
    });
  }
}
