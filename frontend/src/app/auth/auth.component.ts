import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

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
  loading = false;
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

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
    const loginData = {
      email: this.email,
      password: this.password
    };

    try {
      const response: any = await this.http.post('http://localhost:8080/api/auth/login', loginData).toPromise();
      
      if (response && response.token) {
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.router.navigate(['/dashboard']);
      } else {
        throw new Error('Login failed - no token received');
      }
    } catch (error: any) {
      console.error('Login error:', error);
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
    const registerData = {
      nom: this.name,
      email: this.email,
      password: this.password
    };

    try {
      const response: any = await this.http.post('http://localhost:8080/api/auth/register', registerData).toPromise();
      
      if (response) {
        // Switch to login mode after successful registration
        this.isLogin = true;
        this.password = '';
        this.confirmPassword = '';
        this.name = '';
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
  }
} 