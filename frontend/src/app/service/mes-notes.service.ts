import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MesNotesService {
  private apiUrl = '/api/notes';

  constructor(private http: HttpClient) {}

  getNotes(etudiantId: string | number): Observable<any[]> {
    console.log(' MesNotesService - Making API call to:', `${this.apiUrl}/${etudiantId}`);
    

    return this.http.get<any[]>(`${this.apiUrl}/${etudiantId}`);
  }
} 