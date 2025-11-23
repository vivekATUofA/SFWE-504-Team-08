import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ScholarshipsRoutingModule } from './scholarships-routing.module';
import { ScholarshipsComponent } from './scholarships.component'; // Import the new component

@NgModule({
  // Declarations array is empty or removed for standalone components
  declarations: [], 
  imports: [
    CommonModule,
    ScholarshipsRoutingModule,
    ScholarshipsComponent // Import the standalone component
  ]
})
export class ScholarshipsModule { }