# MDD APP (Monde de Dév)

The MDD (Developer's World) social network! Designed specifically for developers, MDD aims to revolutionize professional networking by connecting like-minded individuals and fostering collaboration. The MVP (Minimum Viable Product) is underway, allowing users to subscribe to programming topics, share articles, and engage in discussions.

# Built With

This section show the list of any major frameworks/libraries used in the project. Here are a few examples.

- Angular
- material ui
- spring boot
- spring security

## Requirements

Before installing and running the MDD app, make sure you have the following requirements installed on your machine:

- JAVA 17
- NodeJS 18
- MySQL Server 8
- Angular 17

## Installation

To install and run the app, follow these steps:

Clone the entire project : [https://github.com/ESrebuff/P-6-Developpez-une-application-full-stack-complete.git](https://github.com/ESrebuff/P-6-Developpez-une-application-full-stack-complete.git)

A) **_IN THE BACKEND DIRECTORY_**

1.  Set up a MySQL Server and run the script.sql

2.  data-credential:

    a. Configure your env.properties
    - create your env.properties at root and  add the following

        ```
        spring.jpa.hibernate.ddl-auto=update
        spring.datasource.url=jdbc:mysql:YOUR_URL_TO_YOUR_DB/mdd?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
        spring.datasource.username=YOUR_DB_USERNAME
        spring.datasource.password=YOUR_DB_PASSWORD
        spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
        spring.jpa.show-sql=true
        server.port=YOUR_PORT
        secret-key=YOUR_SECRET_KEY
        ```
    b. Run the following command:
    mvn spring-boot:run

A) ___IN THE FRONTEND DIRECTORY___

2. Install NPM packages

        npm install

3. Start or run the project 
        
        ng start
