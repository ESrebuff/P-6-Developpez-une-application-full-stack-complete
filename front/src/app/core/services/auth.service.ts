import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000/api/auth';
  // private apiUrl = 'URL_DE_VOTRE_API';
  // TODO utiliser des variables d'environement

  private token: string | undefined;

  constructor(private http: HttpClient) { }

  getToken(): string | null {
    return sessionStorage.getItem('jwt');
  }

  setToken(token: string): void {
    sessionStorage.setItem('jwt', token);
  }

  isAuthenticated(): boolean {
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

  getCurrentUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/me`);
  }

  updateUser(credentials: { username: string | undefined, name: string | undefined, password: string | undefined }): Observable<any> {
    return this.http.put(`${this.apiUrl}/me`, credentials)
      .pipe(
        map((response: any) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response;
        })
      );
  }
}