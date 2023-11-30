import { Subject } from "./subject.interface";
import { User } from "./user.interface";

export interface Article {
    id: number;
    title: string;
    content: string;
    author: User;
    subject: Subject
    comments: Comment[];
    createdAt: Date;
    updatedAt: Date;
}
