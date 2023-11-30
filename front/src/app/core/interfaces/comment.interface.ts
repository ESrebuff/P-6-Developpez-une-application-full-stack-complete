import { User } from "./user.interface";

export interface Comment {
    id: number;
    content: string;
    author: User;
    createdAt: Date;
    updatedAt: Date;
}
