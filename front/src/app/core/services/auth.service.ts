import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000/api/auth';

  private token: string | undefined;

  constructor(private http: HttpClient) { }

  getToken(): string | null {
    // Récupère le token depuis la session
    return sessionStorage.getItem('jwt');
  }

  setToken(token: string): void {
    // Stocke le token dans la session
    sessionStorage.setItem('jwt', token);
  }

  isAuthenticated(): boolean {
    // Vérifie la présence du token en session
    return this.getToken() !== null;
  }

  login(credentials: { username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials)
      .pipe(
        map((response: any) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response
        })
      )
  }

  register(credentials: { username: string, name: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, credentials)
      .pipe(
        map((response: any) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response
        })
      )
  }

  logout(): void {
    sessionStorage.removeItem('jwt');
  }
}