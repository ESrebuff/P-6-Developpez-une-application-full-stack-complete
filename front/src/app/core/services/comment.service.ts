import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comment, CommentRequest } from "../interfaces/comment.interface";
import { API_BASE_URL } from "src/app/app-config";

@Injectable({
    providedIn: 'root',
})
export class CommentService {
    private apiUrl = `${API_BASE_URL}/comment`;

    constructor(private http: HttpClient) { }

    createComment(newComment: CommentRequest): Observable<Comment> {
        return this.http.post<Comment>(this.apiUrl, newComment);
    }

}