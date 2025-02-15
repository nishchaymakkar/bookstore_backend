# Bookstore Backend

Bookstore Backend is a RESTful API built using **Spring Boot** and **Kotlin**, designed to handle bookstore management operations efficiently. It includes user authentication, book catalog management, and order processing. The application uses **Docker** to containerize the database for seamless deployment.

## Features

- **User Authentication & Authorization**
- **Book Management (CRUD Operations)**
- **Order Processing & Checkout System**
- **Dockerized MySQL Database**
- **Kotlin Coroutines for Asynchronous Processing**
- **Spring Boot with RESTful APIs**

## Tech Stack

- **Backend**: Spring Boot, Kotlin
- **Database**: MySQL (Containerized using Docker)
- **Dependency Injection**: Spring Boot with Kotlin
- **Security**: Spring Security & JWT Authentication
- **Containerization**: Docker

## Installation & Setup

### Prerequisites
- Java 17+
- Docker & Docker Compose
- Gradle

### Steps to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/nishchaymakkar/bookstore_backend.git
   cd bookstore_backend
   ```

2. **Build the project:**
   ```bash
   ./gradlew build
   ```

3. **Run Docker Containers (PostgreSQL):**
   ```bash
   docker-compose up -d
   ```

4. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

## API Endpoints
Base Url:-
auth:- http://localhost:8080/bookstore
authors:- http://localhost:8080/bookstore/v1/authors
books:- http://localhost:8080/bookstore/v1

| Method | Endpoint              | Description                |
|--------|-----------------------|----------------------------|
| GET    | /books                | Fetch all books            |
| GET    | /books/{isbn}         | Get book details by ID     |
| POST   | /books                | Add a new book             |
| PUT    | /books/{isbn}         | Update book details        |
| DELETE | /books/{isbn}         | Remove a book              |
| POST   | /auth/userRegister    | User registration          |
| POST   | /auth/login           | User login (JWT Token)     |

## Running Tests

To run unit and integration tests, use:
```bash
./gradlew test
```

## Docker Setup

- The project includes a `docker-compose.yml` file to set up the **MySQL** database.
- To stop the containers:
  ```bash
  docker-compose down
  ```


## License

This project is licensed under the MIT License.

---
Developed by **Nishchay Makkar** 🚀
