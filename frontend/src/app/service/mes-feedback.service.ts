import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MesFeedbackService {
  private apiUrl = '/api/feedback';

  constructor(private http: HttpClient) {}

  getFeedbacks(etudiantId: string | number): Observable<any[]> {
    console.log('MesFeedbackService - Making API call to:', `${this.apiUrl}/${etudiantId}`);

    return this.http.get<any[]>(`${this.apiUrl}/${etudiantId}`);
  }
}
