export interface Subject {
    id: number;
    name: string;
    created_at: Date;
    updated_at: Date;
}

export interface Subjects {
    subjects: Subject[];
}