````markdown
# 🔐 Spring Security JWT Authentication

A secure authentication and authorization system built with **Spring Boot**, **Spring Security**, **JWT**, **Spring Data JPA**, **Hibernate**, and **Oracle Database**.

## 📌 Project Overview

This project implements a secure authentication and authorization workflow using **Spring Security** and **JSON Web Tokens (JWT)**.

Users can register and authenticate through the application. After successful authentication, a JWT token is generated and can be used to access protected resources.

The project demonstrates **stateless authentication**, **password encryption using BCrypt**, **JWT validation**, and **role-based authorization** using the Spring Boot ecosystem.

---

## 🚀 Features

- User registration
- User login and authentication
- Secure password hashing using BCrypt
- JWT token generation
- JWT token validation
- JWT authentication filter
- Stateless authentication
- Role-based authorization
- Protected endpoints
- Spring Data JPA integration
- Hibernate ORM
- Oracle Database integration
- Thymeleaf-based web pages
- Environment variables for sensitive configuration

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-Based Authentication |
| Spring Data JPA | Data Access |
| Hibernate | ORM |
| Oracle Database | Relational Database |
| Thymeleaf | Server-Side Web Pages |
| Maven | Build & Dependency Management |
| Postman | API Testing |
| Git | Version Control |
| GitHub | Source Code Hosting |

---

## 🏗️ Authentication Architecture

```text
                    ┌───────────────┐
                    │     Client    │
                    │ Postman / Web │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Login Request │
                    └───────┬───────┘
                            │
                            ▼
                ┌───────────────────────┐
                │   Spring Security     │
                │ AuthenticationManager │
                └───────────┬───────────┘
                            │
                            ▼
                ┌───────────────────────┐
                │  UserDetailsService   │
                └───────────┬───────────┘
                            │
                            ▼
                    ┌───────────────┐
                    │ Oracle Database│
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │   JWT Service │
                    │  Generate JWT │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │   JWT Token   │
                    └───────┬───────┘
                            │
                            ▼
              Authorization: Bearer <JWT>
                            │
                            ▼
                ┌───────────────────────┐
                │ JWT Authentication    │
                │       Filter          │
                └───────────┬───────────┘
                            │
                            ▼
                  ┌───────────────────┐
                  │ Protected Resource│
                  └───────────────────┘
````

---

## 🔑 Authentication Flow

### 1. User Registration

```text
Client
  ↓
Registration Endpoint
  ↓
Validate User Data
  ↓
Encrypt Password with BCrypt
  ↓
Save User to Oracle Database
```

### 2. User Login

```text
Client
  ↓
Login Endpoint
  ↓
Spring Security AuthenticationManager
  ↓
UserDetailsService
  ↓
Oracle Database
  ↓
Password Verification
  ↓
JWT Generation
  ↓
JWT Returned to Client
```

### 3. Accessing Protected Resources

The client sends the JWT with each protected request:

```http
Authorization: Bearer <JWT_TOKEN>
```

The JWT authentication filter:

1. Reads the Authorization header.
2. Extracts the Bearer token.
3. Validates the JWT.
4. Extracts the required claims.
5. Loads the user's details.
6. Creates an authenticated SecurityContext.
7. Allows access to the protected resource.

---

## 🔑 API Endpoints

### Public Endpoints

#### Home

```http
GET /home
```

Accessible without authentication.

#### User Registration

```http
POST /register
```

Used to register a new user.

Example request:

```json
{
  "username": "john",
  "password": "password123",
  "role": "USER"
}
```

### Authentication

#### Login

```http
POST /auth/login
```

Used to authenticate a user and obtain a JWT token.

Example request:

```json
{
  "username": "john",
  "password": "password123"
}
```

After successful authentication, the server returns a JWT token.

---

## 🔒 Authorization

Protected resources require a valid JWT.

The token must be included in the HTTP Authorization header:

```http
Authorization: Bearer <JWT_TOKEN>
```

Spring Security validates the JWT and determines whether the authenticated user can access the requested resource.

---

## 🔐 Password Security

User passwords are never stored as plain text.

The application uses:

```text
BCryptPasswordEncoder
```

to securely hash passwords before storing them in the database.

The password security flow is:

```text
Plain Password
      ↓
BCrypt Password Encoder
      ↓
Hashed Password
      ↓
Oracle Database
```

During authentication, Spring Security verifies the submitted password against the stored BCrypt hash.

---

## 🎟️ JWT Authentication

JSON Web Tokens are used to implement stateless authentication.

After successful login:

```text
Username + Password
        ↓
Authentication
        ↓
JWT Generation
        ↓
JWT Returned to Client
```

The client then sends the JWT with requests to protected resources:

```http
Authorization: Bearer <JWT_TOKEN>
```

The JWT authentication filter extracts and validates the token before allowing access to protected resources.

---

## 🗄️ Database

The application uses **Oracle Database** for persistent user data.

Database access is implemented using:

* Spring Data JPA
* Hibernate
* Oracle JDBC Driver

User information is stored in the database and retrieved during the authentication process.

---

## ⚙️ Configuration

Sensitive credentials are not hard-coded into the application.

Environment variables are used for sensitive configuration.

Example:

```properties
spring.datasource.password=${DB_PASSWORD}
jwt.secretkey=${JWT_SECRET}
```

### Required Environment Variables

```text
DB_PASSWORD=your_database_password
JWT_SECRET=your_jwt_secret
```

> **Important:** Never commit real database passwords, JWT secrets, API keys, or other sensitive credentials to GitHub.

---

## 🧰 Prerequisites

Before running the project, make sure you have:

* Java JDK
* Oracle Database
* Maven or Maven Wrapper
* Git
* Eclipse or IntelliJ IDEA
* Postman for API testing

---

## ▶️ Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/spring-security-jwt-authentication.git
```

Move into the project directory:

```bash
cd spring-security-jwt-authentication
```

### 2. Configure Oracle Database

Make sure Oracle Database is running and the required database/schema is available.

Configure the required database properties in:

```text
src/main/resources/application.properties
```

Sensitive credentials should be provided through environment variables.

### 3. Configure Environment Variables

Set the following environment variables:

```text
DB_PASSWORD
JWT_SECRET
```

Use your own values when running the application.

### 4. Build the Project

On Windows:

```bash
mvnw.cmd clean install
```

On Linux/macOS:

```bash
./mvnw clean install
```

### 5. Run the Application

Run the Spring Boot application from Eclipse or IntelliJ IDEA.

Alternatively, on Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## 🧪 Testing with Postman

The application's APIs can be tested using Postman.

### Registration

Send a `POST` request to:

```text
/register
```

with the required user information.

### Login

Send a `POST` request to:

```text
/auth/login
```

with valid credentials.

After successful authentication, the server returns a JWT token.

### Protected Requests

For protected endpoints, use:

```http
Authorization: Bearer <JWT_TOKEN>
```

In Postman, the token can also be provided through:

**Authorization → Bearer Token**

---

## 📂 Project Structure

The project follows a layered Spring Boot architecture.

```text
src
└── main
    ├── java
    │   └── com.example.springSecurity
    │       ├── Controller
    │       ├── Service
    │       ├── Repository
    │       ├── Entity
    │       └── ...
    │
    └── resources
        ├── application.properties
        ├── static
        └── templates
```

---

## 📋 Security Components

### SecurityConfig

Responsible for configuring Spring Security, including:

* Security filter chain
* Public endpoints
* Protected endpoints
* Authentication provider
* Password encoder
* Session management

### MyUserDetailService

Implements:

```java
UserDetailsService
```

and loads user information from the database during authentication.

### JwtService

Responsible for JWT-related operations such as:

* Generating JWT tokens
* Extracting claims
* Extracting usernames
* Validating tokens

### JwtAuthenticationFilter

Extends:

```java
OncePerRequestFilter
```

and processes JWT tokens from incoming HTTP requests.

### UserRepository

Provides database access for user information using Spring Data JPA.

---

## 🛡️ Security Design

The project follows several security practices:

* Passwords are hashed using BCrypt.
* Authentication is stateless.
* JWT is used for authenticated requests.
* Sensitive configuration is provided through environment variables.
* Protected endpoints require authentication.
* Authorization is handled by Spring Security.
* Database access is handled through JPA/Hibernate.

---

## 📈 Future Improvements

Possible improvements for future versions include:

* Refresh token implementation
* OAuth 2.0
* OpenID Connect
* Email verification
* Password reset functionality
* Unit and integration testing
* Swagger/OpenAPI documentation
* Docker containerization
* Redis caching
* CI/CD pipeline
* Cloud deployment

---

## 📚 Learning Outcomes

This project provided practical experience with:

* Spring Boot
* Spring Security
* Authentication and Authorization
* JWT-based authentication
* BCrypt password hashing
* REST API development
* Spring Data JPA
* Hibernate ORM
* Oracle Database
* Dependency Injection
* Maven
* Postman API testing
* Environment-based configuration
* Git and GitHub

---

## 👨‍💻 Author

**Alina**

Software Engineering Student

This project was developed as part of my backend development portfolio to demonstrate practical experience with secure authentication, authorization, JWT, database integration, and the Spring Boot ecosystem.

