import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MesPresenceService {
  private apiUrl = '/api/presence';

  constructor(private http: HttpClient) {}

  getNotes(etudiantId: string | number): Observable<any[]> {
    console.log(' MesPresenceService - Making API call to:', `${this.apiUrl}/${etudiantId}`);


    return this.http.get<any[]>(`${this.apiUrl}/${etudiantId}`);
  }
}
