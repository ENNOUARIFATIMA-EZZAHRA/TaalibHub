import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('user') || 'null'));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  login(email: string, password: string): Observable<any> {
    console.log('AuthService - Attempting login for:', email);
    return this.http.post<any>('/api/auth/login', { email, password })
      .pipe(map(response => {
        // Nettoyage avant stockage
        localStorage.removeItem('jwt_token');
        localStorage.removeItem('token');
        localStorage.removeItem('user');

        if (response && response.token) {
          console.log('AuthService - Storing token and user data');
          localStorage.setItem('jwt_token', response.token);
          localStorage.setItem('token', response.token);

          if (response.user) {
            localStorage.setItem('user', JSON.stringify(response.user));
            console.log('AuthService - User data stored:', response.user);
            console.log('AuthService - User role:', response.user.role);
          }

          this.currentUserSubject.next(response.user);

          console.log('AuthService - jwt_token stored:', localStorage.getItem('jwt_token') ? '✅' : '❌');
          console.log('AuthService - token stored:', localStorage.getItem('token') ? '✅' : '❌');
          console.log('AuthService - user stored:', localStorage.getItem('user') ? '✅' : '❌');
        } else {
          // Si la réponse n'est pas correcte, on vide tout
          localStorage.removeItem('jwt_token');
          localStorage.removeItem('token');
          localStorage.removeItem('user');
          this.currentUserSubject.next(null);
          console.error('AuthService - Login failed, response:', response);
        }
        return response;
      }));
  }

  register(nom: string, prenom: string, email: string, password: string, role: string, specialite?: string): Observable<any> {
    return this.http.post<any>('/api/auth/register', { nom, prenom, email, password, role, specialite });
  }

  logout() {
    console.log('AuthService - Logging out, clearing storage');
    localStorage.removeItem('token');
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
    console.log(' AuthService - Storage cleared successfully');
  }

  isAuthenticated(): boolean {

    let token = localStorage.getItem('jwt_token');
    let user = localStorage.getItem('user');


    if (!token) {
      token = localStorage.getItem('token');
    }

    const isAuth = !!(token || user);
    console.log('AuthService - isAuthenticated:', isAuth);
    return isAuth;
  }

  getToken(): string | null {
    let token = localStorage.getItem('jwt_token');


    if (!token) {
      token = localStorage.getItem('token');
    }

    return token;
  }

  getUser(): any {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  isStudent(): boolean {
    const user = this.getUser();
    return user && user.role === 'ETUDIANT';
  }
}
