-- Delete all tables
CREATE DATABASE mdd;
USE mdd;

CREATE TABLE `USERS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE `SUBJECTS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE SUBSCRIPTIONS (
    user_id INT,
    subject_id INT,
    subscription_date TIMESTAMP,
    PRIMARY KEY (user_id, subject_id),
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (subject_id) REFERENCES SUBJECTS(id)
);

CREATE TABLE `ARTICLES` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    subject_id INT,
    author_id INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (subject_id) REFERENCES Subjects(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);

CREATE TABLE `COMMENTS` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    article_id INT,
    author_id INT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (article_id) REFERENCES Articles(id),
    FOREIGN KEY (author_id) REFERENCES Users(id)
);



INSERT INTO SUBJECTS (name, created_at, updated_at) VALUES
('Intelligence Artificielle (IA)', NOW(), NOW()),
('Développement Logiciel', NOW(), NOW()),
('Sécurité Informatique', NOW(), NOW()),
('Réseaux et Systèmes', NOW(), NOW()),
('Base de Données', NOW(), NOW());
