import { Component, Input, OnInit } from '@angular/core';
import { EtudiantDashboardService } from '../../service/etudiant-dashboard.service';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { HttpClient } from '@angular/common/http';

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

  constructor(
    private dashboardService: EtudiantDashboardService,
    private authService: AuthService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit() {
    console.log('🔍 Navbar - Checking authentication status...');
    console.log('🔍 Navbar - Token:', this.authService.getToken() ? '✅ Present' : '❌ Missing');
    console.log('🔍 Navbar - User:', this.authService.getUser() ? '✅ Present' : '❌ Missing');
    
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
      // استخدام صورة افتراضية من الإنترنت بدلاً من ملف محلي
      return 'https://via.placeholder.com/40x40/2563eb/ffffff?text=U';
    }
    return this.photoUrl;
  }

  // دالة لاختبار حالة الـ authentication
  testAuth() {
    console.log('🔍 Navbar - Testing authentication...');
    console.log('🔍 Navbar - localStorage jwt_token:', localStorage.getItem('jwt_token'));
    console.log('🔍 Navbar - localStorage token:', localStorage.getItem('token'));
    console.log('🔍 Navbar - localStorage user:', localStorage.getItem('user'));
    console.log('🔍 Navbar - AuthService isAuthenticated:', this.authService.isAuthenticated());
    
    // اختبار الوصول لصفحة mes-cours
    console.log('🔍 Navbar - Testing access to /etudiante/mes-cours');
    
    // اختبار الـ backend
    this.http.post('/api/auth/test', {}).subscribe({
      next: (response) => {
        console.log('✅ Backend test successful:', response);
      },
      error: (error) => {
        console.error('❌ Backend test failed:', error);
      }
    });
  }

  // دالة تسجيل الخروج
  logout() {
    console.log('🔍 Navbar - Logging out...');
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    console.log('🔍 Navbar - Cleared localStorage');
    this.router.navigate(['/auth']);
  }
} 