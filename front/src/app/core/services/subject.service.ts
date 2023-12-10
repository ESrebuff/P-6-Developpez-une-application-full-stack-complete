import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject, Subjects } from '../interfaces/subject.interface';

@Injectable({
  providedIn: 'root',
})

export class SubjectService {
  private apiUrl = 'http://localhost:9000/api/subject';

  constructor(private http: HttpClient) {}

  getAllSubjects(): Observable<Subjects> {
    return this.http.get<Subjects>(this.apiUrl);
  }

  getSubjectById(id: number): Observable<Subject> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Subject>(url);
  }

  createSubject(newSubject: Subject): Observable<Subject> {
    return this.http.post<Subject>(this.apiUrl, newSubject);
  }

}