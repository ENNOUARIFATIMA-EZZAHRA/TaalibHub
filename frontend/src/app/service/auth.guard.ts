import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    console.log('AuthGuard - Checking authentication...');


    let token = localStorage.getItem('jwt_token');
    let user = localStorage.getItem('user');

    if (!token) {
      token = localStorage.getItem('token');
      console.log('AuthGuard - Using fallback token:', token ? 'Found' : 'Not found');
    }

    console.log('AuthGuard - Token:', token ? 'Present' : 'Missing');
    console.log('AuthGuard - User:', user ? 'Present' : 'Missing');

    if (user) {
      const userObj = JSON.parse(user);
      console.log('AuthGuard - User data:', userObj);
      console.log(' AuthGuard - User num:', userObj?.num);
    }


    if (token) {
      console.log('AuthGuard - Token found, allowing access');
      return true;
    }


    if (user) {
      console.log('AuthGuard - User found, allowing access');
      return true;
    }


    console.log(' AuthGuard - No token or user found, redirecting to login');
    console.log('AuthGuard - Current URL will be redirected to /auth');
    this.router.navigate(['/auth']);
    return false;
  }
}
