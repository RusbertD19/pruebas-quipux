import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  

@Component({
  selector: 'app-lists',
  standalone: true,
  imports: [CommonModule],  
  templateUrl: './lists.component.html'
})
export class ListsComponent {
  listas = [{ name: 'Lista 1' }, { name: 'Lista 2' }];
}