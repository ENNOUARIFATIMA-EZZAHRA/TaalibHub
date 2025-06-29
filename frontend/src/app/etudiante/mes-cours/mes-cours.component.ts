import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EtudiantCoursesService } from '../mes-cours/etudiant-courses.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-mes-cours',
  templateUrl: './mes-cours.component.html',
  styleUrls: ['./mes-cours.component.css'],
  standalone: true,
  imports: [CommonModule, NavbarComponent]
})
export class MesCoursComponent implements OnInit {
  cours: any[] = [];

  constructor(private coursesService: EtudiantCoursesService) {}

  ngOnInit() {
    this.coursesService.getCourses().subscribe(data => {
      this.cours = data;
    });
  }
} 