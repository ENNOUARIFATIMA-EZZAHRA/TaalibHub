import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class EtudiantDashboardService {
  constructor(private http: HttpClient) {}

  getDashboard(num: number) {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    
    return this.http.get<any>(`/api/etudiant/dashboard/${num}`, { headers });
  }
} 