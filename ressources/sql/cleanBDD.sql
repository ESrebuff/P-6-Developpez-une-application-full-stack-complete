-- Delete all tables
DROP TABLE USERS;
DROP TABLE SUBJECTS;
DROP TABLE ARTICLES;
DROP TABLE COMMENTS;

-- Create all tables
CREATE TABLE `USERS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT email_unique UNIQUE (email)
);

CREATE TABLE `SUBJECTS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE `ARTICLES` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    subject_id INT,
    author_id INT,
    publication_date DATETIME NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES Subjects(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);

CREATE TABLE `COMMENTS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    article_id INT,
    author_id INT,
    publication_date DATETIME NOT NULL,
    FOREIGN KEY (article_id) REFERENCES Articles(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);
