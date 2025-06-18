import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ListDTO } from '../list-dto/list-dto.component';
import { CancionDTO } from '../list-dto/cancionDTO';
import { ConfirmDialogComponent } from '../confirm-dialog.component/confirm-dialog.component';

@Component({
  selector: 'app-lists',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatSnackBarModule,
    MatListModule,
    MatExpansionModule,
    MatDialogModule
],
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent {
  nuevaLista: ListDTO = {
    id: 0,
    nombre: '',
    descripcion: '',
    canciones: []
  };

  dataSource = new MatTableDataSource<ListDTO>();
  displayedColumns: string[] = ['nombre', 'descripcion', 'canciones', 'acciones'];

  constructor(
    private http: HttpClient,
    private router: Router,
    private dialog: MatDialog
  ) {
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
      next: () => {
        alert('Lista creada correctamente');
        this.nuevaLista = { id: 0, nombre: '', descripcion: '', canciones: [] };
        this.cargarListas();
      },
      error: (err) => {
        alert('Error al crear lista');
        console.error(err);
      }
    });
  }

  cargarListas() {
    this.http.get<ListDTO[]>('http://localhost:8080/lists').subscribe({
      next: (data) => this.dataSource.data = data,
      error: (err) => console.error('Error cargando listas:', err)
    });
  }
 eliminarLista(nombre: string) {
  if (!nombre) return;

  const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    width: '350px',
    data: { title: 'Confirmar eliminación', message: `¿Eliminar la lista "${nombre}"?` }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.http.delete(`http://localhost:8080/lists/${encodeURIComponent(nombre)}`).subscribe({
        next: () => this.cargarListas(),
        error: err => {
          if (err.status === 404) {
            alert('La lista no existe.');
          } else {
            console.error('Error eliminando lista:', err);
          }
        }
      });
    }
  });
}



    logout() {
  this.router.navigate(['/']);  }
}
