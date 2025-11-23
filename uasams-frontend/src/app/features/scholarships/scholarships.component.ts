import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-scholarships',
  standalone: true,
  // FIX: Using templateUrl to point to the new HTML file
  templateUrl: './scholarships.component.html', 
  // FIX: Using styleUrls to point to a CSS file (creating this file is optional)
  imports: [CommonModule, RouterModule]
})
export class ScholarshipsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log('ScholarshipsComponent loaded successfully.');
  }

}