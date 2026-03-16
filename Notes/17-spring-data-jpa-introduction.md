# 17 — Spring Data JPA Introduction

## 1. Introduction

Modern applications interact with databases frequently. Writing raw SQL queries for every database operation can be time-consuming and error-prone.

To simplify database interaction, Java provides **ORM frameworks**. In Spring Boot, the most commonly used technology stack is:

```
Spring Boot
     ↓
Spring Data JPA
     ↓
JPA (Specification)
     ↓
Hibernate (Implementation)
     ↓
Database (MySQL, PostgreSQL, etc.)
```

This combination allows developers to interact with the database using **Java objects instead of SQL queries**.

---

# 2. What is ORM

**ORM** stands for **Object Relational Mapping**.

ORM is a technique that maps **Java objects to database tables**.

Instead of writing SQL queries manually, we work with Java objects and the ORM framework handles the database operations.

### Example

Database Table:

```
users
--------------------------------
id | name | email
--------------------------------
1  | John | john@gmail.com
```

Java Class:

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
    private String email;

}
```

Mapping:

| Database | Java   |
|----------|--------|
| Table    | Class  |
| Row      | Object |
| Column   | Field  |

With ORM, saving data looks like:

```
userRepository.save(user);
```

Instead of writing SQL like:

```sql
INSERT INTO users(name,email) VALUES ('John','john@gmail.com');
```

---

# 3. Advantages of ORM

Using ORM provides several benefits:

### 1. Reduces SQL Boilerplate

Developers do not need to write SQL queries for basic operations.

### 2. Improves Productivity

CRUD operations become very simple.

Example:

```
save()
findById()
findAll()
delete()
```

### 3. Database Independence

The same code works with different databases such as:

* MySQL
* PostgreSQL
* Oracle
* H2

### 4. Object-Oriented Approach

Developers work with **Java objects instead of tables and rows**.

---

# 4. What is JPA

**JPA** stands for **Java Persistence API**.

JPA is **not a framework**, it is a **specification** that defines how Java objects should interact with relational databases.

It provides:

* Standard annotations
* Standard interfaces
* Standard rules for ORM

Examples of JPA annotations:

```
@Entity
@Table
@Id
@Column
@OneToMany
@ManyToOne
```

Example Entity:

```java
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
```

JPA only defines **what should happen**, but not **how it happens internally**.

For the actual implementation we need a JPA provider.

---

# 5. What is Hibernate

**Hibernate** is the **most popular implementation of JPA**.

Hibernate performs the actual work of:

* Converting objects into SQL queries
* Managing database connections
* Executing queries
* Mapping results back to Java objects

Example:

```
productRepository.save(product);
```

Hibernate internally generates SQL:

```sql
INSERT INTO product (name) VALUES ('Laptop');
```

Developers do not need to write this SQL manually.

---

# 6. What is Spring Data JPA

**Spring Data JPA** is a Spring module that simplifies working with JPA.

It provides ready-made repository interfaces to perform database operations without writing implementation code.

Example Repository:

```java
public interface UserRepository extends JpaRepository<User, Long> {

}
```

This single interface automatically provides:

```
save()
findById()
findAll()
deleteById()
count()
existsById()
```

Developers do not need to implement these methods.

---

# 7. Technology Stack Relationship

Understanding the relationship between these technologies is important.

```
Spring Boot
     ↓
Spring Data JPA
     ↓
JPA (Specification)
     ↓
Hibernate (Implementation)
     ↓
Relational Database
```

Explanation:

| Layer           | Role                       |
|-----------------|----------------------------|
| Spring Boot     | Application framework      |
| Spring Data JPA | Simplifies database access |
| JPA             | ORM specification          |
| Hibernate       | JPA implementation         |
| Database        | Stores actual data         |

---

# 8. Example Flow in Spring Boot

Example API request:

```
POST /users
```

Request JSON:

```json
{
  "name": "John",
  "email": "john@gmail.com"
}
```

Flow inside application:

```
Controller
     ↓
Service
     ↓
Repository (Spring Data JPA)
     ↓
Hibernate
     ↓
Database
```

Example repository usage:

```
userRepository.save(user);
```

Hibernate converts it to SQL and stores it in the database.

---

# 9. Basic Dependencies (Gradle)

To use Spring Data JPA in a Spring Boot project, we include the following dependency.

```gradle
dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

}
```

This starter includes:

* Spring Data JPA
* Hibernate
* JPA API
* Database integration support

---

# 10. Database Configuration Example

Example configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Explanation:

| Property            | Purpose                   |
|---------------------|---------------------------|
| datasource.url      | Database connection       |
| datasource.username | DB username               |
| datasource.password | DB password               |
| ddl-auto            | Table generation strategy |
| show-sql            | Displays SQL queries      |

---

# 11. Key Annotations in JPA

Some commonly used JPA annotations:

| Annotation        | Purpose                       |
|-------------------|-------------------------------|
| `@Entity`         | Marks class as database table |
| `@Table`          | Specifies table name          |
| `@Id`             | Primary key                   |
| `@GeneratedValue` | Auto-generates ID             |
| `@Column`         | Maps field to column          |

Example:

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

}
```

---

# 12. Summary

Spring Data JPA simplifies database access in Spring Boot applications.

Key concepts:

| Concept         | Description                     |
|-----------------|---------------------------------|
| ORM             | Maps objects to database tables |
| JPA             | Java ORM specification          |
| Hibernate       | JPA implementation              |
| Spring Data JPA | Simplifies JPA usage            |

Technology hierarchy:

```
Spring Boot
   ↓
Spring Data JPA
   ↓
JPA
   ↓
Hibernate
   ↓
Database
```

Using this stack allows developers to **build database-driven applications quickly with minimal boilerplate code**.

---
