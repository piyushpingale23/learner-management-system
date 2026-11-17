# Learner Management System

A comprehensive **Spring Boot REST API project** built for learning and understanding modern backend application development using Spring Boot, Spring Data JPA, and H2 Database.

This project demonstrates how to design and develop a complete backend system by implementing REST APIs, layered architecture, entity relationships, data persistence, exception handling, and database integration.

---

## Project Objectives

The primary goal of this project is to learn and implement:

* Spring Boot Fundamentals
* REST API Development
* Layered Architecture
* Spring Data JPA
* Hibernate ORM
* Entity Relationship Mapping
* Database Integration
* DTO Pattern
* Exception Handling
* CRUD Operations
* API Design Best Practices

---

## Tech Stack

| Technology      | Version |
|-----------------|---------|
| Java            | 21      |
| Spring Boot     | Latest  |
| Spring Web      | Latest  |
| Spring Data JPA | Latest  |
| Hibernate       | Latest  |
| H2 Database     | Latest  |
| Gradle          | Latest  |

---

## Features

### Learner Management

* Create Learners
* Retrieve Learner Details
* Update Learner Information
* Delete Learners

### REST API Development

* RESTful Endpoints
* Request Mapping
* Path Variables
* Request Parameters
* Request Body Validation
* HTTP Status Codes

### Database Integration

* H2 In-Memory Database
* Spring Data JPA Repositories
* Automatic Table Creation
* SQL Query Logging

### Exception Handling

* Global Exception Handling
* Custom Exceptions
* Meaningful Error Responses

### DTO Layer

* Request DTOs
* Response DTOs
* Separation of Entity and API Models

---

## Spring Boot Concepts Covered

### Core Spring Boot

* Auto Configuration
* Dependency Injection
* Bean Management
* Application Properties
* Component Scanning

### Spring Web

* REST Controllers
* Request Mapping
* ResponseEntity
* API Design

### Spring Data JPA

* Repository Pattern
* CRUD Repository Operations
* Custom Query Methods
* Entity Mapping

### Hibernate

* ORM (Object Relational Mapping)
* Automatic Schema Generation
* Relationship Mapping

---

## JPA Relationships Covered

### One-to-One

Used when one record is associated with exactly one other record.

Example:

```text
Learner ↔ Profile
```

### One-to-Many / Many-to-One

Used when one parent can have multiple child records.

Example:

```text
Instructor → Courses
```

Important Points:

* Foreign Key exists on the Many side.
* Many side is the owner of the relationship.
* One side acts as the back reference.

### Many-to-Many

Used when multiple records can be associated with multiple records.

Example:

```text
Learners ↔ Courses
```

Important Points:

* Implemented using a Join Table.
* Join Table stores primary keys of both entities.
* Maintains database normalization.

---

## Project Structure

```text
src/main/java
└── com.airtribe.learnermanagementsystem
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    ├── exception
    └── LearnerManagementSystemApplication
```

### Package Responsibilities

| Package    | Responsibility          |
|------------|-------------------------|
| controller | REST API Endpoints      |
| service    | Business Logic          |
| repository | Database Operations     |
| entity     | JPA Entities            |
| dto        | Request/Response Models |
| exception  | Exception Handling      |

---

## Running the Application

### Clone Repository

```bash
git clone <repository-url>
```

### Navigate to Project

```bash
cd learner-management-system
```

### Run Using Gradle

```bash
./gradlew bootRun
```

### Run Using IDE

Execute:

```text
LearnerManagementSystemApplication.java
```

---

## Application URL

After successful startup:

```text
http://localhost:8080
```

---

## H2 Database Console

H2 is used as an in-memory database for development and learning purposes.

### H2 Console URL

```text
http://localhost:8080/h2-console
```

### Login Details

| Field    | Value            |
|----------|------------------|
| JDBC URL | jdbc:h2:mem:LMDB |
| Username | sa               |
| Password | (empty)          |

---

## H2 Configuration

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:mem:lmsdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Database Verification

After starting the application:

1. Open H2 Console
2. Login using H2 credentials
3. Click Connect
4. Execute:

```sql
SELECT * FROM LEARNER;
```

Replace `LEARNER` with the desired table name.

---

## Learning Outcomes

After completing this project, you will understand:

* Spring Boot Application Flow
* REST API Design and Development
* Layered Architecture
* Dependency Injection
* JPA and Hibernate
* Entity Relationships
* DTO Pattern
* Exception Handling
* Database Integration
* CRUD Operations
* Repository Pattern

---

## Future Enhancements

* Spring Security
* JWT Authentication
* Swagger / OpenAPI Documentation
* MySQL/PostgreSQL Integration
* Pagination and Sorting
* Validation Framework
* Docker Support
* Unit and Integration Testing
* Role-Based Access Control (RBAC)

---

## Author

**Piyush Ingale**

This project was built as a hands-on learning exercise to gain practical experience in Spring Boot, REST APIs, JPA, Hibernate, and backend application development.
