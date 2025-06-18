// src/app/services/list.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ListDTO } from '../dto/list-dto';

@Injectable({
  providedIn: 'root'
})
export class ListService {
  private baseUrl = 'http://localhost:8080/lists';

  constructor(private http: HttpClient) {}

  getAll(): Observable<ListDTO[]> {
    return this.http.get<ListDTO[]>(this.baseUrl);
  }

  getOne(name: string): Observable<ListDTO> {
    return this.http.get<ListDTO>(`${this.baseUrl}/${name}`);
  }

  create(list: ListDTO): Observable<ListDTO> {
    return this.http.post<ListDTO>(this.baseUrl, list);
  }

  delete(name: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${name}`);
  }
   crearLista(lista: ListDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}`, lista);
  }

  obtenerListas(): Observable<ListDTO[]> {
    return this.http.get<ListDTO[]>(this.baseUrl);
  }
}
