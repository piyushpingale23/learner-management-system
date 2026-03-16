# 05 — Spring Boot Starters (Gradle Based)

## 1. Introduction

Spring Boot provides **Starter Dependencies** to simplify dependency management in Spring applications.

A **Spring Boot Starter** is a **predefined dependency descriptor** that bundles together commonly used libraries required for a specific functionality.

Instead of manually adding multiple dependencies, developers can include **one starter dependency**, and Spring Boot automatically includes all required libraries.

This greatly simplifies project configuration and setup.

---

# 2. Why Spring Boot Starters Exist

Before Spring Boot, developers had to manually add multiple dependencies for a single feature.

Example: Building a web application required dependencies such as:

* Spring Core
* Spring MVC
* Jackson
* Validation libraries
* Logging libraries

Managing these dependencies was difficult because:

* Developers had to find compatible versions
* Dependency conflicts were common
* Project setup became complex

Spring Boot solved this problem by introducing **Starter Dependencies**.

A starter groups all necessary dependencies into **a single dependency**.

---

# 3. How Starter Dependencies Work

Starter dependencies follow the naming convention:

```text
spring-boot-starter-*
```

When a starter is included in the project:

1. Spring Boot downloads all required libraries.
2. Autoconfiguration automatically configures the required beans.
3. The application becomes ready to use that feature.

Example Gradle dependency:

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

This single dependency automatically includes:

* Spring MVC
* Jackson (JSON processing)
* Validation
* Embedded Tomcat
* Logging

---

# 4. Common Spring Boot Starters

Spring Boot provides many starter dependencies for different functionalities.

---

# 4.1 spring-boot-starter-web

Used for building **web applications and REST APIs**.

### Includes

* Spring MVC
* Jackson (JSON serialization/deserialization)
* Validation
* Embedded Tomcat server
* Logging libraries

### Gradle Dependency

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

### Example REST Controller

```java
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello Spring Boot";
    }

}
```

---

# 4.2 spring-boot-starter-data-jpa

Used for **database operations using JPA and Hibernate**.

### Includes

* Spring Data JPA
* Hibernate ORM
* Transaction management
* JPA APIs

### Gradle Dependency

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```

### Example Repository

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

---

# 4.3 spring-boot-starter-security

Used to add **authentication and authorization** to applications.

### Includes

* Spring Security
* Security filters
* Authentication providers

### Gradle Dependency

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
}
```

After adding this dependency, Spring Boot automatically enables **basic authentication**.

Example default login credentials:

```text
Username: user
Password: generated at startup
```

---

# 4.4 spring-boot-starter-validation

Used for **input validation** using Jakarta Bean Validation.

### Includes

* Jakarta Validation API
* Hibernate Validator

### Gradle Dependency

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-validation'
}
```

### Example DTO

```java
public class UserDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

}
```

### Example Controller

```java
@PostMapping("/users")
public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
    return ResponseEntity.ok("User created");
}
```

---

# 5. Other Common Starters

| Starter                        | Purpose                          |
|--------------------------------|----------------------------------|
| spring-boot-starter-web        | Build web apps and REST APIs     |
| spring-boot-starter-data-jpa   | Database operations using JPA    |
| spring-boot-starter-security   | Authentication and authorization |
| spring-boot-starter-validation | Input validation                 |
| spring-boot-starter-test       | Testing support                  |
| spring-boot-starter-actuator   | Monitoring and production tools  |
| spring-boot-starter-thymeleaf  | Template engine for web apps     |

Example Gradle dependencies:

```gradle
dependencies {

    implementation 'org.springframework.boot:spring-boot-starter-web'

    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    implementation 'org.springframework.boot:spring-boot-starter-security'

    implementation 'org.springframework.boot:spring-boot-starter-validation'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'

}
```

---

# 6. Advantages of Starter Dependencies

Spring Boot starters provide several advantages.

### Simplified Dependency Management

Developers only add **one dependency instead of many**.

---

### Compatible Library Versions

Spring Boot manages compatible versions of all libraries automatically using **Spring Boot dependency management**.

Developers do not need to manually specify versions.

---

### Faster Project Setup

Projects can be created quickly because developers don't need to search for required dependencies.

---

### Seamless Integration with Auto Configuration

Starter dependencies work together with **Spring Boot Auto Configuration**.

When a starter is added:

1. Required libraries are downloaded.
2. Spring Boot automatically configures necessary components.

---

# 7. Checking Dependencies Inside a Starter

You can inspect dependencies included in a starter using Gradle.

Example command:

```bash
./gradlew dependencies
```

This command prints the dependency tree of the project.

---

# 8. Summary

Spring Boot Starter Dependencies simplify application development by bundling commonly used libraries into a single dependency.

Key benefits:

* Simplified dependency management
* Compatible library versions
* Faster project setup
* Seamless integration with autoconfiguration

Common starters include:

* `spring-boot-starter-web`
* `spring-boot-starter-data-jpa`
* `spring-boot-starter-security`
* `spring-boot-starter-validation`

These starters allow developers to quickly add functionality to their applications with minimal configuration.

---
