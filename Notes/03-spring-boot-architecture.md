# 03 — Spring Boot Architecture

## 1. Introduction

Spring Boot architecture is designed to simplify the development of **production-ready Java applications** by reducing configuration and providing built-in features.

Spring Boot internally uses the **Spring Framework**, but adds additional layers such as:

* Auto Configuration
* Starter Dependencies
* Embedded Servers
* Production-ready features

These components work together to make application development faster and easier.

---

# 2. High-Level Spring Boot Architecture

A typical Spring Boot application follows a **layered architecture**.

```text
Client
   ↓
DispatcherServlet
   ↓
Controller Layer
   ↓
Service Layer
   ↓
Repository Layer
   ↓
Database
```

### Layer Explanation

**Client**

* Sends HTTP requests to the application.
* Example: Browser, Mobile App, Postman.

**DispatcherServlet**

* Central request handler in Spring MVC.
* Routes incoming requests to the appropriate controller.

**Controller Layer**

* Handles incoming HTTP requests.
* Converts request data into Java objects.

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<User> getUsers() {
        return List.of();
    }

}
```

**Service Layer**

* Contains business logic.
* Processes application rules and validations.

Example:

```java
@Service
public class UserService {

    public List<User> getUsers() {
        return List.of();
    }

}
```

**Repository Layer**

* Handles database operations.
* Uses Spring Data JPA or Hibernate.

Example:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

# 3. Spring Boot Internal Components

Spring Boot architecture contains several internal components that simplify development.

Main components:

1. Auto Configuration
2. Starter Dependencies
3. Embedded Servers
4. Spring Boot CLI (optional)
5. Actuator (production monitoring)

---

# 4. Auto Configuration

Auto Configuration is one of the **most important features of Spring Boot**.

It automatically configures application components based on the dependencies available in the project.

### How Auto Configuration Works

Spring Boot checks the classpath and automatically configures beans.

Example:

If the project contains this dependency:

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Spring Boot automatically configures:

* DataSource
* Hibernate
* TransactionManager
* JPA repositories

Developers do **not need to configure these manually**.

---

### Auto Configuration Annotation

Autoconfiguration is enabled using:

```
@EnableAutoConfiguration
```

However, this annotation is already included in:

```
@SpringBootApplication
```

---

### Example

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

This annotation enables:

* Auto configuration
* Component scanning
* Configuration support

---

# 5. Starter Dependencies

Spring Boot provides **starter dependencies** to simplify dependency management.

A starter is a **predefined dependency bundle** that includes commonly used libraries for a specific functionality.

Example:

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This single dependency includes:

* Spring MVC
* Jackson (JSON processing)
* Validation
* Embedded Tomcat
* Logging

---

### Common Starter Dependencies

| Starter                        | Purpose                              |
|--------------------------------|--------------------------------------|
| spring-boot-starter-web        | Build REST APIs and web applications |
| spring-boot-starter-data-jpa   | Database access using JPA            |
| spring-boot-starter-security   | Security and authentication          |
| spring-boot-starter-validation | Input validation                     |
| spring-boot-starter-test       | Testing support                      |

Starter dependencies **reduce dependency management complexity**.

---

# 6. Embedded Servers

Traditional Spring applications required **external application servers**.

Spring Boot solves this problem using **embedded servers**.

Supported embedded servers:

* **Tomcat (default)**
* **Jetty**
* **Undertow**

---

### How Embedded Servers Work

When a Spring Boot application starts:

1. The application starts an embedded server.
2. The server listens on a specific port.
3. Requests are handled directly by the application.

Example configuration:

```properties
server.port=9090
```

Run application:

```bash
java -jar application.jar
```

The server starts automatically.

Example access:

```
http://localhost:9090
```

---

# 7. Spring Boot Application Startup Process

When a Spring Boot application starts, the following process occurs:

```text
1. main() method executes
2. SpringApplication.run() starts Spring container
3. ApplicationContext is created
4. Auto Configuration loads
5. Beans are created
6. Embedded server starts
7. Application becomes ready to accept requests
```

Example startup code:

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

---

# 8. Spring Boot Request Flow

When a client sends a request:

```text
Client Request
      ↓
Embedded Server (Tomcat)
      ↓
DispatcherServlet
      ↓
Handler Mapping
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

Response flow:

```text
Database
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
DispatcherServlet
   ↓
Client Response
```

---

# 9. Benefits of Spring Boot Architecture

Spring Boot architecture provides several advantages:

* Reduces configuration complexity
* Simplifies dependency management
* Provides embedded servers
* Enables faster application development
* Supports microservices architecture
* Provides production-ready tools

---

# 10. Summary

Spring Boot architecture is built on top of the Spring Framework and simplifies application development through powerful features.

Core components include:

* **Auto Configuration** for automatic setup
* **Starter Dependencies** for simplified dependency management
* **Embedded Servers** for easier deployment

These features allow developers to focus on **business logic instead of configuration**.

---
