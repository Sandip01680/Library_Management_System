# Library Management System

## Project Structure

library-management-system/

├── backend/                (Java Spring Boot)
│   ├── src/main/java/com/library/
│   │   ├── LibraryApplication.java
│   │
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── BookController.java
│   │   │   ├── TransactionController.java
│   │
│   │   ├── model/
│   │   │   ├── User.java
│   │   │   ├── Book.java
│   │   │   ├── Transaction.java
│   │
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   ├── BookService.java
│   │   │   ├── TransactionService.java
│   │   │   ├── FineService.java
│   │
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   ├── BookRepository.java
│   │   │   ├── TransactionRepository.java
│   │
│   │   ├── util/
│   │   │   ├── FineCalculator.java
│   │
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│
│   ├── src/main/resources/
│   │   ├── application.properties
│
│   ├── pom.xml
│
├── frontend/               (HTML, CSS, JS)
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── style.css
│   ├── script.js
│
└── README.md


## Features

- User Registration and Login
- Add, Update, Delete Books
- Issue and Return Books
- Fine Calculation

## Tech Stack

- Backend: Java Spring Boot
- Frontend: HTML, CSS, JavaScript
- Database: MySQL