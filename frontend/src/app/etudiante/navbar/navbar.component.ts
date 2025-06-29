import { Component, Input, OnInit } from '@angular/core';
import { EtudiantDashboardService } from '../dashboard/etudiant-dashboard.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar-etudiant',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  standalone: true,
  imports: [RouterModule]
})
export class NavbarComponent implements OnInit {
  @Input() nom: string = '';
  @Input() prenom: string = '';
  @Input() statut: string = 'Etudiant';
  @Input() photoUrl: string = '';

  constructor(private dashboardService: EtudiantDashboardService) {}

  ngOnInit() {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    const num = user?.num;
    if (num) {
      this.dashboardService.getDashboard(num).subscribe(data => {
        this.nom = data.etudiant?.nom || '';
        this.prenom = data.etudiant?.prenom || '';
        // Si tu ajoutes une photo dans le backend, décommente la ligne suivante :
        // this.photoUrl = data.etudiant?.photoUrl || '';
      });
    }
  }

  get avatarUrl(): string {
    if (!this.photoUrl || this.photoUrl.trim() === '' || this.photoUrl === 'null' || this.photoUrl === 'undefined') {
      return 'assets/avatar-real.jpg';
    }
    return this.photoUrl;
  }
} 