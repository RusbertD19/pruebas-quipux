import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListDTO } from '../list-dto/list-dto.component';
import { CancionDTO } from '../list-dto/cancionDTO';

@Component({
  selector: 'app-lists',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent {
 nuevaLista: ListDTO = {
  nombre: '',
  descripcion: '',
  canciones: []
};


  listas: any[] = [];

  constructor(private http: HttpClient) {
    this.cargarListas();
  }

  agregarCancion() {
  const nuevaCancion: CancionDTO = {
    titulo: '',
    artista: '',
    album: '',
    anno: '',
    genero: ''
  };
  this.nuevaLista.canciones.push(nuevaCancion);
}


  eliminarCancion(index: number) {
    this.nuevaLista.canciones.splice(index, 1);
  }

  crearLista() {
    this.http.post('http://localhost:8080/lists', this.nuevaLista).subscribe({
      next: (res) => {
        alert('Lista creada correctamente');
        this.nuevaLista = { nombre: '', descripcion: '', canciones: [] };
        this.cargarListas();
      },
      error: (err) => {
        alert('Error al crear lista');
        console.error(err);
      }
    });
  }

  cargarListas() {
    this.http.get<any[]>('http://localhost:8080/lists').subscribe({
      next: (data) => this.listas = data,
      error: (err) => console.error('Error cargando listas:', err)
    });
  }
}
