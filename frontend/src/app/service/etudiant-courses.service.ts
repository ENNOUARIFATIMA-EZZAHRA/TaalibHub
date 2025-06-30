import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class EtudiantCoursesService {
  private apiUrl = '/api/etudiant/cours';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<any[]> {
    console.log('EtudiantCoursesService - Making API call to:', this.apiUrl);


    return this.http.get<any[]>(this.apiUrl);
  }
}
