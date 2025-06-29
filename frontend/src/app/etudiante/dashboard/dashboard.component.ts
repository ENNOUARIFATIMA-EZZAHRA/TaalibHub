import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EtudiantDashboardService } from './etudiant-dashboard.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

interface Note {
  valeur: number;
  cours?: { titre: string };
}

interface Cours {
  titre: string;
  jour?: string;
  horaire?: string;
  enseignant?: { nom: string; prenom: string };
}

interface Feedback {
  contenu: string;
  date: string;
  cours?: Cours;
}

interface Etudiant {
  nom: string;
  prenom: string;
  photoUrl?: string;
  // Ajoute d'autres propriétés si besoin
}

interface DashboardData {
  etudiant: Etudiant;
  moyenneGenerale: number;
  tauxPresence: number;
  modulesSuivis: number;
  devoirsEnAttente: number;
  dernieresNotes: Note[];
  prochainsCours: Cours[];
  messagesEnseignants: Feedback[];
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  dashboard: DashboardData | null = null;
  loading = false;
  error: string | null = null;

  constructor(private dashboardService: EtudiantDashboardService) {}

  ngOnInit() {
    this.loading = true;
    this.dashboardService.getDashboard(1234)
      .pipe(
        catchError(err => {
          this.error = "Erreur lors du chargement du dashboard.";
          this.loading = false;
          return of(null);
        })
      )
      .subscribe(data => {
        this.dashboard = data;
        this.loading = false;
      });
  }

  getNotePourcentage(note: Note): number {
    return note && note.valeur ? Math.round((note.valeur / 20) * 100) : 0;
  }

  getNomEnseignant(cours?: Cours): string {
    if (!cours || !cours.enseignant) return '';
    return `${cours.enseignant.prenom || ''} ${cours.enseignant.nom || ''}`.trim();
  }
}
