import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Subscription } from '../interfaces/subscription.interface';
import { Observable } from "rxjs";
import { API_BASE_URL } from "src/app/app-config";

@Injectable({
    providedIn: 'root',
})
export class SubscriptionService {
    private apiUrl = `${API_BASE_URL}/subscription`;

    constructor(private http: HttpClient) {}

    getAllSubscriptions(): Observable<Subscription[]> {
        return this.http.get<any>(this.apiUrl);
    }

    createSubscription(subjectId: number): Observable<Subscription> {
        const newSubscription = { subjectId: subjectId };
        return this.http.post<Subscription>(this.apiUrl, newSubscription);
    }

    deleteSubscription(subjectId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${subjectId}`);
    }

}