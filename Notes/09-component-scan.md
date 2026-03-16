# 09 — Component Scan

## 1. Introduction

**Component Scanning** is a mechanism in Spring that automatically detects **Spring Beans** in the application.

Instead of manually registering beans in configuration files, Spring scans the project packages and **automatically creates beans for classes annotated with specific annotations**.

These annotations are called **Stereotype Annotations**.

Example:

```java
@Service
public class UserService {

}
```

When the application starts, Spring scans the package, detects this annotation, and registers `UserService` as a **Spring Bean**.

---

# 2. What is Component Scanning?

Component Scanning means:

> Spring automatically searches for classes annotated with specific annotations and registers them as beans in the Spring IoC Container.

Spring scans packages starting from the **main application class package**.

Example main class:

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

`@SpringBootApplication` internally includes:

```
@ComponentScan
```

So Spring automatically scans all sub-packages.

Example structure:

```
com.example.project
│
├── controller
│   └── UserController
│
├── service
│   └── UserService
│
├── repository
│   └── UserRepository
```

All these classes are automatically detected if they contain component annotations.

---

# 3. Component Scan Annotation

Spring provides the `@ComponentScan` annotation to control package scanning.

Example:

```java
@Configuration
@ComponentScan(basePackages = "com.example.project")
public class AppConfig {

}
```

This tells Spring to scan the specified package for components.

In Spring Boot, this configuration is usually **automatic**.

---

# 4. @Component Annotation

`@Component` is the **base annotation for all Spring-managed beans**.

Any class annotated with `@Component` becomes a **Spring Bean**.

Example:

```java
@Component
public class EmailService {

    public void sendEmail() {
        System.out.println("Email sent");
    }

}
```

Spring will detect this class and create its object automatically.

---

# 5. @Service Annotation

`@Service` is a specialized version of `@Component`.

It is used to mark classes in the **Service Layer**.

Example:

```java
@Service
public class UserService {

    public String getUser() {
        return "User Data";
    }

}
```

Purpose:

* Contains business logic
* Acts as an intermediary between controller and repository

Layer architecture:

```
Controller → Service → Repository
```

---

# 6. @Repository Annotation

`@Repository` is used for **data access layer components**.

Example:

```java
@Repository
public class UserRepository {

    public String findUser() {
        return "User From Database";
    }

}
```

Benefits:

* Automatically detected as a bean
* Enables database exception translation

In real applications, repositories usually extend **Spring Data JPA interfaces**.

Example:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
```

---

# 7. @Controller Annotation

`@Controller` is used in **Spring MVC applications** to handle web requests.

Example:

```java
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}
```

Here:

* The method returns a **view name**
* Typically used with **Thymeleaf or JSP**

---

# 8. @RestController Annotation

`@RestController` is used to create **REST APIs**.

It combines two annotations:

```
@Controller + @ResponseBody
```

Meaning:

* Methods return **JSON data**
* Not a view

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "User List";
    }

}
```

Response:

```
User List
```

In real applications, it returns **JSON objects**.

Example:

```java
@GetMapping
public User getUser() {
    return new User(1, "John");
}
```

Response:

```json
{
  "id": 1,
  "name": "John"
}
```

---

# 9. REST API Development in Spring Boot

Spring Boot is widely used for building **RESTful APIs**.

REST APIs allow communication between systems using **HTTP protocols**.

Common HTTP methods:

| Method | Purpose       |
|--------|---------------|
| GET    | Retrieve data |
| POST   | Create data   |
| PUT    | Update data   |
| DELETE | Delete data   |

---

## Example REST API

### Controller

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "All Products";
    }

    @PostMapping
    public String createProduct() {
        return "Product Created";
    }

}
```

---

## API Endpoints

```
GET  /products
POST /products
```

Example request:

```
GET http://localhost:8080/products
```

Response:

```
All Products
```

---

# 10. Layered Architecture in Spring Boot

Typical project structure:

```
controller
service
repository
model
```

Flow:

```
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database
```

Example flow:

```
GET /users
        ↓
UserController
        ↓
UserService
        ↓
UserRepository
        ↓
Database
```

---

# 11. Best Practices

Recommended practices for component scanning:

* Keep the main class at the **root package**
* Use layered architecture
* Use specific annotations (`@Service`, `@Repository`) instead of generic `@Component`
* Avoid placing beans outside the scan path

Example root structure:

```
com.example.project
│
├── Application.java
├── controller
├── service
├── repository
├── model
```

---

# 12. Summary

Component Scanning allows Spring to automatically detect and register beans.

Common stereotype annotations:

| Annotation        | Layer               |
|-------------------|---------------------|
| `@Component`      | Generic bean        |
| `@Service`        | Business logic      |
| `@Repository`     | Data access         |
| `@Controller`     | Web MVC controller  |
| `@RestController` | REST API controller |

Key points:

* Enabled by `@ComponentScan`
* Automatically included in `@SpringBootApplication`
* Helps reduce configuration
* Enables automatic bean detection

Component scanning is a **core feature that powers Spring Boot's automatic configuration and bean management**.

---
