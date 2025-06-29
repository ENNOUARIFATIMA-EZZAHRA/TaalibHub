import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class EtudiantDashboardService {
  constructor(private http: HttpClient) {}

  getDashboard(num: number) {
    return this.http.get<any>(`/api/etudiant/dashboard/${num}`);
  }
} 