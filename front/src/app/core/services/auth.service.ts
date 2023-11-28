import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000/api/auth';
  private _isAuthenticated = false;

  constructor(private http: HttpClient) {}

  // Méthode pour vérifier l'état de connexion
  isAuthenticated(): boolean {
    // Logique pour vérifier le JWT ou toute autre méthode d'authentification
    return this._isAuthenticated;
  }

  login(credentials: { username: string, password: string }): Observable<any> {
    const url = `${this.apiUrl}/login`;
    const test = this.http.post(url, credentials);
    console.log('test', test)
    return test;
  }
}