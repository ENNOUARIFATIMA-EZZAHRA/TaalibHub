import { Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { AuthGuard } from './service/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/auth', pathMatch: 'full' },
  { path: 'auth', component: AuthComponent },
  { path: 'dashboard', loadComponent: () => import('./dashboard/dashboard.component').then(m => m.DashboardComponent), canActivate: [AuthGuard] },
  { path: 'etudiante/dashboard', loadComponent: () => import('./etudiante/dashboard/dashboard.component').then(m => m.DashboardComponent), canActivate: [AuthGuard] },
  { path: 'etudiante/mes-cours', loadComponent: () => import('./etudiante/mes-cours/mes-cours.component').then(m => m.MesCoursComponent), canActivate: [AuthGuard] },
  { path: 'etudiante/mes-notes', loadComponent: () => import('./etudiante/mes-notes/mes-notes.component').then(m => m.MesNotesComponent), canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/auth' }
];