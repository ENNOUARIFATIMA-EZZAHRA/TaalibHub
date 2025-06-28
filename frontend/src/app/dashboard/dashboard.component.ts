import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="min-h-screen bg-gray-50">
      <nav class="bg-white shadow-lg">
        <div class="max-w-7xl mx-auto px-4">
          <div class="flex justify-between h-16">
            <div class="flex items-center">
              <h1 class="text-xl font-bold text-gray-800">TaaliibHub Dashboard</h1>
            </div>
            <div class="flex items-center">
              <button 
                (click)="logout()"
                class="btn-primary"
              >
                Logout
              </button>
            </div>
          </div>
        </div>
      </nav>
      
      <main class="max-w-7xl mx-auto py-6 px-4">
        <div class="bg-white rounded-lg shadow p-6 animate-fade-in">
          <h2 class="text-2xl font-bold text-gray-800 mb-4">Welcome to TaaliibHub!</h2>
          <p class="text-gray-600">You have successfully logged in to your account.</p>
        </div>
      </main>
    </div>
  `
})
export class DashboardComponent {
  constructor(private router: Router) {}

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['/auth']);
  }
} 