import { User } from "./user.interface";

export interface Comment {
    id: number;
    articleId: number;
    content: string;
    author: User;
    createdAt: Date;
    updatedAt: Date;
}

export interface CommentRequest {
    content: string;
    articleId: number;
}
