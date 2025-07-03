import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  isLogin = true;
  showPassword = false;
  showConfirmPassword = false;
  email = '';
  password = '';
  confirmPassword = '';
  name = '';
  prenom = '';
  loading = false;
  errorMessage = '';
  role = 'ETUDIANT'; // Par défaut
  specialite = '';

  constructor(private authService: AuthService, private router: Router) {}

  validateEmail(email: string): boolean {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  validatePassword(password: string): boolean {
    return password.length >= 8;
  }

  validateConfirmPassword(): boolean {
    return this.password === this.confirmPassword;
  }

  isFormValid(): boolean {
    if (this.isLogin) {
      return this.email.trim() !== '' && this.validateEmail(this.email) &&
             this.password.trim() !== '' && this.validatePassword(this.password);
    } else {
      return this.name.trim() !== '' && this.email.trim() !== '' && this.validateEmail(this.email) &&
             this.password.trim() !== '' && this.validatePassword(this.password) &&
             this.validateConfirmPassword();
    }
  }

  async handleSubmit() {
    if (!this.isFormValid()) return;

    this.loading = true;
    this.errorMessage = '';

    try {
      if (this.isLogin) {
        await this.login();
      } else {
        await this.register();
      }
    } catch (error: any) {
      this.errorMessage = error.message || 'An error occurred';
    } finally {
      this.loading = false;
    }
  }

  private async login() {
    try {
      const response = await this.authService.login(this.email, this.password).toPromise();

      console.log('🔐 AuthComponent - Login response:', response);

      if (response && response.token) {
        console.log('🔐 AuthComponent - Storing token in localStorage');


        localStorage.setItem('jwt_token', response.token);
        localStorage.setItem('token', response.token);

        if (response.user) {
          localStorage.setItem('user', JSON.stringify(response.user));
        }

        console.log('🔐 AuthComponent - Token stored successfully');
        console.log('🔐 AuthComponent - localStorage token:', localStorage.getItem('jwt_token'));
        console.log('🔐 AuthComponent - localStorage user:', localStorage.getItem('user'));


        if (response.user && response.user.role === 'ETUDIANT') {
          this.router.navigate(['/etudiante/dashboard']);
        } else {
          this.router.navigate(['/dashboard']);
        }
      } else {
        throw new Error('Login failed - no token received');
      }
    } catch (error: any) {
      console.error('🔐 AuthComponent - Login error:', error);
      if (error.status === 403) {
        throw new Error('Invalid email or password');
      } else if (error.status === 0) {
        throw new Error('Cannot connect to server. Please check if backend is running.');
      } else {
        throw new Error(error.error?.message || 'Login failed');
      }
    }
  }

  private async register() {
    try {
      const response = await this.authService.register(this.name, this.prenom, this.email, this.password, this.role, this.specialite).toPromise();

      if (response) {
        // Switch to login mode after successful registration
        this.isLogin = true;
        this.password = '';
        this.confirmPassword = '';
        this.name = '';
        this.prenom = '';
        this.specialite = '';
        alert('Registration successful! Please login.');
      } else {
        throw new Error('Registration failed - no response received');
      }
    } catch (error: any) {
      console.error('Registration error:', error);
      if (error.status === 400) {
        throw new Error(error.error?.message || 'Registration failed - invalid data');
      } else if (error.status === 0) {
        throw new Error('Cannot connect to server. Please check if backend is running.');
      } else {
        throw new Error(error.error?.message || 'Registration failed');
      }
    }
  }

  toggleForm() {
    this.isLogin = !this.isLogin;
    this.errorMessage = '';
    this.password = '';
    this.confirmPassword = '';
    this.name = '';
    this.prenom = '';
    this.specialite = '';
  }
}
