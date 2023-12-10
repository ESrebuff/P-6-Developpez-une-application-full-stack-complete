import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Article, ArticleRequest, Articles } from "../interfaces/article.interface";

@Injectable({
    providedIn: 'root',
})
export class ArticleService {
    private apiUrl = 'http://localhost:9000/api/article';

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