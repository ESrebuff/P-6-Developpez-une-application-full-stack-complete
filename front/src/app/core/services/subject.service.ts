import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Subject, Subjects } from '../interfaces/subject.interface';

@Injectable({
  providedIn: 'root',
})

export class SubjectService {

  constructor(private http: HttpClient) {}

  getAllSubjects(): Observable<Subjects> {
    return this.http.get<Subjects>('http://localhost:9000/api/subject');
  }

}