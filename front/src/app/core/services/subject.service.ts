import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { switchMap, map } from 'rxjs/operators';
import { Subject, Subjects } from '../interfaces/subject.interface';
import { SubscriptionService } from './subscription.service';
import { Subscription } from '../interfaces/subscription.interface';

@Injectable({
  providedIn: 'root',
})

export class SubjectService {
  private apiUrl = 'http://localhost:9000/api/subject';

  constructor(private http: HttpClient, private subscriptionService: SubscriptionService) { }

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

  getAllSubjectsWithSubscriptionStatus(): Observable<Subject[]> {
    return this.getAllSubjects().pipe(
      switchMap((subjects: Subjects) => {
        return this.subscriptionService.getAllSubscriptions().pipe(
          map((subscriptions: Subscription[]) => {
            return subjects.subjects.map(subject => {
              const isSubscribed = subscriptions.some(subscription => subscription.subjectId === subject.id);
              return { ...subject, subscribed: isSubscribed };
            });
          })
        );
      })
    );
  }
  
  getSubjectsBySubscriptions(): Observable<Subject[]> {
    return this.subscriptionService.getAllSubscriptions().pipe(
      switchMap((subscriptions: Subscription[]) => {
        const subjectObservables: Observable<Subject>[] = subscriptions.map(subscription => {
          return this.getSubjectById(subscription.subjectId).pipe(
            map((subject: Subject) => {
              subject.subscribed = true;
              return subject;
            })
          );
        });
        return forkJoin(subjectObservables);
      })
    );
  }

}