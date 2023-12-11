import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Comment, CommentRequest } from "../interfaces/comment.interface";

@Injectable({
    providedIn: 'root',
})
export class CommentService {
    private apiUrl = 'http://localhost:9000/api/comment';

    constructor(private http: HttpClient) { }

    createComment(newComment: CommentRequest): Observable<Comment> {
        return this.http.post<Comment>(this.apiUrl, newComment);
    }

}