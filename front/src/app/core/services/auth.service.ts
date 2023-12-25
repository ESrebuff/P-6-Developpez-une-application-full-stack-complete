import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Jwt, User } from '../interfaces/user.interface';
import { API_BASE_URL } from 'src/app/app-config';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = `${API_BASE_URL}/auth`;

  constructor(private http: HttpClient) { }

  getToken(): string | null {
    return sessionStorage.getItem('jwt');
  }

  setToken(token: string): void {
    sessionStorage.setItem('jwt', token);
  }

  decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decodedPayload = atob(payload);
    return JSON.parse(decodedPayload);
  }

  isTokenExpired(): boolean {
    const token = this.getToken();
    if (token) {
      const decodedToken: any = this.decodeToken(token);
      const expirationDate = new Date(decodedToken.exp * 1000);
      const currentDate = new Date();
      return expirationDate <= currentDate;
    }
    return true;
  }


  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  login(credentials: { username: string, password: string }): Observable<Jwt> {
    return this.http.post(`${this.apiUrl}/login`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response
        })
      )
  }

  register(credentials: { username: string, name: string, password: string }): Observable<Jwt> {
    return this.http.post(`${this.apiUrl}/register`, credentials)
      .pipe(
        map((response: Jwt) => {
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

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me`);
  }

  updateUser(credentials: { username: string | undefined, name: string | undefined, password: string | undefined }): Observable<Jwt> {
    return this.http.put(`${this.apiUrl}/me`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response;
        })
      );
  }
}