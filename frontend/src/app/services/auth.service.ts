import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    const body = { username, password };
    return this.http.post(this.apiUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    });
  }
  getToken(): string | null {
    return localStorage.getItem('token');
  }
}
