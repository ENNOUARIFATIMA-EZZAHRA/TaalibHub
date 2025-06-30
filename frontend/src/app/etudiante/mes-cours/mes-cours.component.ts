import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EtudiantCoursesService } from '../../service/etudiant-courses.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-mes-cours',
  templateUrl: './mes-cours.component.html',
  styleUrls: ['./mes-cours.component.css'],
  standalone: true,
  imports: [CommonModule, NavbarComponent]
})
export class MesCoursComponent implements OnInit {
  cours: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(
    private coursesService: EtudiantCoursesService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loading = true;
    this.error = null;


    const user = JSON.parse(localStorage.getItem('user') || '{}');
    console.log('Etudiant user (MesCours):', user);

    let num = user?.num;
    console.log('Etudiant num (MesCours):', num);


    if (!num || num === 0) {
      num = user?.id;
      console.log('Etudiant using ID as fallback (MesCours):', num);
    }

    if (!num) {
      this.error = 'Utilisateur non connecté - Numéro étudiant manquant ou invalide (num = ' + user?.num + ', id = ' + user?.id + ')';
      this.loading = false;
      console.log('MesCoursComponent - Redirecting to auth due to missing/invalid user num:', user?.num);
      this.router.navigate(['/auth']);
      return;
    }

    console.log('MesCoursComponent - Loading courses...');

    this.coursesService.getCourses()
      .pipe(
        catchError(err => {
          console.error('MesCoursComponent - Erreur lors du chargement des cours:', err);
          this.error = 'Erreur lors du chargement';
          this.loading = false;
          return of([]);
        })
      )
      .subscribe(data => {
        console.log('MesCoursComponent - Courses loaded successfully:', data);
        this.cours = data;
        this.loading = false;
      });
  }
}
