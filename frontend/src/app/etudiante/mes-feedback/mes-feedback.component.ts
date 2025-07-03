import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MesFeedbackService } from '../../service/mes-feedback.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-mes-feedback',
  templateUrl: './mes-feedback.component.html',
  styleUrls: ['./mes-feedback.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class MesFeedbackComponent implements OnInit {
  feedbacks: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(
    private feedbackService: MesFeedbackService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loading = true;
    this.error = null;

    const user = JSON.parse(localStorage.getItem('user') || '{}');
    console.log('👤 Etudiant user (MesFeedback):', user);
    let num = user?.num;
    console.log('📘 Etudiant num (MesFeedback):', num);


    if (!num || num === 0) {
      num = user?.id;
      console.log('📘 Etudiant using ID as fallback (MesFeedback):', num);
    }

    if (!num) {
      this.error = 'Utilisateur non connecté - Numéro étudiant manquant ou invalide (num = ' + user?.num + ', id = ' + user?.id + ')';
      this.loading = false;
      console.log('🔍 MesFeedbackComponent - Redirecting to auth due to missing/invalid user num:', user?.num);
      this.router.navigate(['/auth']);
      return;
    }

    console.log('🔍 MesFeedbackComponent - Loading feedbacks for student:', num);

    this.feedbackService.getFeedbacks(num)
      .pipe(
        catchError(err => {
          console.error('🔍 MesFeedbackComponent - Erreur lors du chargement des feedbacks:', err);
          this.error = 'Erreur lors du chargement';
          this.loading = false;
          return of([]);
        })
      )
      .subscribe(data => {
        console.log('🔍 MesFeedbackComponent - Feedbacks loaded successfully:', data);
        // Map backend feedback to UI format
        this.feedbacks = (data || []).map((fb: any) => {
          // Extract fields safely
          const enseignant = fb.enseignant || {};
          const note = fb.note || {};
          const cours = note.cours || {};
          const contenu = fb.contenu || '';
          const dateStr = fb.date || new Date().toISOString();
          const dateObj = new Date(dateStr);
          const now = new Date();
          // Nouveau if less than 7 days old
          const diffDays = (now.getTime() - dateObj.getTime()) / (1000 * 3600 * 24);
          const nouveau = diffDays <= 7;
          // Warning if contenu contains 'attention' (case-insensitive)
          const type = /attention/i.test(contenu) ? 'warning' : 'success';
          return {
            prof: enseignant.nomComplet || enseignant.nom || 'Professeur inconnu',
            matiere: cours.titre || 'Matière inconnue',
            date: dateStr,
            message: contenu,
            nouveau,
            type
          };
        });
        this.loading = false;
      });
  }
}
