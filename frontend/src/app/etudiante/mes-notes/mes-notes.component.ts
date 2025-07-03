import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MesNotesService } from '../../service/mes-notes.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-mes-notes',
  templateUrl: './mes-notes.component.html',
  styleUrls: ['./mes-notes.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent]
})
export class MesNotesComponent implements OnInit {
  notes: any[] = [];
  moyenne: number = 0;
  nbEvaluations: number = 0;
  loading: boolean = false;
  error: string | null = null;

  constructor(
    private notesService: MesNotesService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loading = true;
    this.error = null;
    
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    console.log('👤 Etudiant user:', user);
    let num = user?.num;
    console.log('📘 Etudiant num:', num);

    // إذا كان num = 0 أو غير موجود، استخدم ID كبديل مؤقت
    if (!num || num === 0) {
      num = user?.id;
      console.log('📘 Etudiant using ID as fallback:', num);
    }

    if (!num) {
      this.error = 'Utilisateur non connecté - Numéro étudiant manquant ou invalide (num = ' + user?.num + ', id = ' + user?.id + ')';
      this.loading = false;
      console.log('🔍 MesNotesComponent - Redirecting to auth due to missing/invalid user num:', user?.num);
      this.router.navigate(['/auth']);
      return;
    }

    console.log('🔍 MesNotesComponent - Loading notes for student:', num);

    this.notesService.getNotes(num)
      .pipe(
        catchError(err => {
          console.error('🔍 MesNotesComponent - Erreur lors du chargement des notes:', err);
          this.error = 'Erreur lors du chargement';
          this.loading = false;
          return of([]);
        })
      )
      .subscribe((data: any) => {
        console.log('🔍 MesNotesComponent - Notes loaded successfully:', data);
        this.notes = data;
        this.nbEvaluations = data.length;
        this.moyenne = data.length
          ? Math.round((data.map((n: any) => n.valeur).reduce((a: number, b: number) => a + b, 0) / data.length) * 100) / 100
          : 0;
        this.loading = false;
      });
  }
} 