# 08 — Beans and Bean Lifecycle

## 1. What is a Bean?

In Spring, a **Bean** is simply an **object that is created, managed, and controlled by the Spring IoC Container**.

In a normal Java application, developers manually create objects using `new`.

Example:

```java
UserService userService = new UserService();
```

In Spring, objects are **automatically created and managed by the container**. These objects are called **Spring Beans**.

Example:

```java
@Service
public class UserService {

}
```

Spring detects this class, creates its object, and manages its lifecycle.

---

# 2. Spring IoC Container and Beans

The **Spring IoC Container** is responsible for managing beans.

Responsibilities of the container:

* Creating beans
* Injecting dependencies
* Managing bean lifecycle
* Destroying beans when the application stops

Common container used in Spring Boot:

```
ApplicationContext
```

---

# 3. Ways to Define Beans

Spring beans can be defined using several methods.

Most common methods:

1. `@Component`
2. `@Bean`

---

# 4. @Component Annotation

`@Component` is used to mark a class as a **Spring Bean**.

When Spring Boot starts, it performs **component scanning** and automatically detects classes annotated with `@Component`.

Example:

```java
@Component
public class EmailService {

}
```

Spring will automatically create and manage the object of this class.

---

## Specialized Component Annotations

Spring provides specialized versions of `@Component`.

| Annotation        | Purpose             |
|-------------------|---------------------|
| `@Component`      | Generic Spring Bean |
| `@Service`        | Service layer       |
| `@Repository`     | Data access layer   |
| `@Controller`     | MVC controller      |
| `@RestController` | REST API controller |

Example:

```java
@Service
public class UserService {

}
```

---

# 5. @Bean Annotation

`@Bean` is used when we want to **manually define a bean inside a configuration class**.

It is typically used when:

* Creating beans for **third-party libraries**
* Custom bean creation logic is required

Example:

```java
@Configuration
public class AppConfig {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }

}
```

Here, Spring will register `EmailService` as a bean in the container.

---

# 6. Bean Lifecycle

The **Bean Lifecycle** represents the stages a bean goes through from **creation to destruction**.

Spring manages the complete lifecycle of beans.

Main lifecycle stages:

```
Instantiation
Dependency Injection
Initialization
Ready to Use
Destroy
```

---

# 7. Bean Lifecycle Steps

## 1. Instantiation

Spring creates the bean object.

Example:

```java
UserService userService = new UserService();
```

This creation is handled by the Spring container.

---

## 2. Dependency Injection

After bean creation, Spring injects all required dependencies.

Example:

```java
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
```

Spring injects `UserRepository` into `UserService`.

---

## 3. Initialization

After dependencies are injected, Spring performs **initialization logic**.

This can include:

* `@PostConstruct`
* `InitializingBean`
* Custom init methods

Example using `@PostConstruct`:

```java
@Service
public class NotificationService {

    @PostConstruct
    public void init() {
        System.out.println("Notification Service Initialized");
    }

}
```

This method runs **after dependency injection is completed**.

---

## 4. Ready to Use

After initialization, the bean is **ready to be used by the application**.

Spring will provide this bean whenever another component needs it.

Example:

```java
@Autowired
private NotificationService notificationService;
```

---

## 5. Destroy Phase

When the application shuts down, Spring destroys beans.

Cleanup logic can be executed using:

* `@PreDestroy`
* `DisposableBean`
* Custom destroy method

Example:

```java
@Service
public class NotificationService {

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning resources...");
    }

}
```

This runs **before the bean is removed from the container**.

---

# 8. Bean Lifecycle Diagram

```
Spring Application Start
        ↓
Bean Instantiation
        ↓
Dependency Injection
        ↓
@PostConstruct (Initialization)
        ↓
Bean Ready to Use
        ↓
Application Running
        ↓
@PreDestroy
        ↓
Bean Destroyed
```

---

# 9. Bean Scope (Brief Overview)

Spring beans can have different **scopes**.

Default scope is **Singleton**.

| Scope       | Description                  |
|-------------|------------------------------|
| singleton   | One instance per container   |
| prototype   | New instance each time       |
| request     | One bean per HTTP request    |
| session     | One bean per HTTP session    |
| application | One bean per web application |

Example:

```java
@Component
@Scope("prototype")
public class TaskProcessor {

}
```

---

# 10. Summary

A **Spring Bean** is an object managed by the Spring IoC container.

Key points:

* Beans are automatically created by Spring
* Defined using `@Component`, `@Service`, `@Repository`, `@Controller`, or `@Bean`
* Spring manages the full bean lifecycle

Bean lifecycle stages:

```
1. Instantiation
2. Dependency Injection
3. Initialization
4. Ready to Use
5. Destroy
```

Understanding beans and their lifecycle is essential for building **Spring Boot applications**.

---
