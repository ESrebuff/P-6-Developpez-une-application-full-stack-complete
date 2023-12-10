export interface Subject {
    id: number;
    name: string;
    created_at: Date;
    updated_at: Date;
    subscribed?: boolean;
}

export interface Subjects {
    subjects: Subject[];
}