import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MesPresenceService } from '../../service/presence.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-mes-presence',
  standalone: true,
  imports: [CommonModule, HttpClientModule, NavbarComponent],
  templateUrl: './mes-presence.component.html',
  styleUrls: ['./mes-presence.component.css']
})
export class MesPresenceComponent implements OnInit {
  tauxPresence: number = 0;
  nbPresences: number = 0;
  nbRetards: number = 0;
  nbAbsences: number = 0;
  totalSeances: number = 0;
  presences: any[] = [];

  constructor(private presenceService: MesPresenceService) {}

  ngOnInit(): void {
    // Replace '1' with the actual student ID as needed
    this.presenceService.getNotes('1').subscribe((data: any) => {
      this.tauxPresence = data.tauxPresence || 0;
      this.nbPresences = data.nbPresences || 0;
      this.nbRetards = data.nbRetards || 0;
      this.nbAbsences = data.nbAbsences || 0;
      this.presences = data.historique || [];
      this.totalSeances = (this.presences.length) || 0;
    });
  }
} 