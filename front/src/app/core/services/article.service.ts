import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Article, ArticleRequest, Articles } from "../interfaces/article.interface";
import { API_BASE_URL } from "src/app/app-config";

@Injectable({
    providedIn: 'root',
})
export class ArticleService {
    private apiUrl = `${API_BASE_URL}/article`;

    constructor(private http: HttpClient) { }

    getAllArticles(): Observable<Article[]> {
        return this.http.get<Article[]>(this.apiUrl);
    }

    getArticleById(id: number): Observable<Article> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<Article>(url);
    }

    createArticle(newArticle: ArticleRequest): Observable<Article> {
        return this.http.post<Article>(this.apiUrl, newArticle);
    }


}