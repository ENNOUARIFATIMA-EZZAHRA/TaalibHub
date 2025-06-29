import { Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';

export const routes: Routes = [
  { path: '', redirectTo: '/auth', pathMatch: 'full' },
  { path: 'auth', component: AuthComponent },
  { path: 'login', redirectTo: '/auth', pathMatch: 'full' },
  { path: 'register', redirectTo: '/auth', pathMatch: 'full' },
  { path: 'dashboard', loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent) },
  { path: 'etudiante/dashboard', loadComponent: () => import('./etudiante/dashboard/dashboard.component').then(m => m.DashboardComponent) },
  { path: 'etudiante/mes-cours', loadComponent: () => import('./etudiante/mes-cours/mes-cours.component').then(m => m.MesCoursComponent) },
  { path: '**', redirectTo: '/auth' }
];
