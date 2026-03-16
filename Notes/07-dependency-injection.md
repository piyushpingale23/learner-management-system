# 07 — Dependency Injection (DI)

## 1. Introduction

**Dependency Injection (DI)** is a design pattern used in the Spring Framework to achieve **loose coupling between classes**.

Instead of a class creating its own dependencies, the dependencies are **provided (injected) by the Spring container**.

This makes the application:

* Easier to maintain
* Easier to test
* More modular

Spring implements Dependency Injection using the concept of **Inversion of Control (IoC)**.

---

# 2. Inversion of Control (IoC)

## What is IoC?

**Inversion of Control (IoC)** means that the control of object creation and dependency management is transferred from the application code to the **Spring Container**.

In traditional programming, classes create their own dependencies.

Example (without IoC):

```java
public class UserService {

    private UserRepository userRepository = new UserRepository();

}
```

Problems:

* Tight coupling
* Hard to test
* Difficult to replace dependencies

---

## With IoC

Spring creates the objects and injects dependencies automatically.

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

Here, the **Spring container provides the UserRepository dependency**.

---

# 3. Spring IoC Container

The **Spring IoC Container** is responsible for:

* Creating objects (Beans)
* Managing object lifecycle
* Injecting dependencies

Common IoC containers:

| Container          | Description                        |
|--------------------|------------------------------------|
| BeanFactory        | Basic container                    |
| ApplicationContext | Advanced container (commonly used) |

Spring Boot internally uses **ApplicationContext**.

---

# 4. Dependency Injection

Dependency Injection is the process where **objects receive their dependencies from an external source** rather than creating them internally.

Example:

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
```

Here:

* `OrderService` depends on `PaymentService`
* Spring automatically injects `PaymentService`

---

# 5. Types of Dependency Injection

Spring supports three types of Dependency Injection.

1. Constructor Injection
2. Setter Injection
3. Field Injection

---

# 6. Constructor Injection

Dependencies are injected through the **constructor of a class**.

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

Spring automatically detects the constructor and injects the dependency.

Example repository:

```java
@Repository
public class UserRepository {

}
```

---

## Advantages

* Ensures required dependencies are provided
* Supports immutability (`final` fields)
* Easier to write unit tests
* Recommended by Spring

---

# 7. Setter Injection

Dependencies are injected using **setter methods**.

Example:

```java
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
```

Spring calls the setter method to inject the dependency.

---

## Advantages

* Allows optional dependencies
* Useful when dependencies can change

---

## Disadvantages

* Dependencies can be changed after object creation
* Not suitable for mandatory dependencies

---

# 8. Field Injection

Dependencies are injected directly into fields using `@Autowired`.

Example:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
```

---

## Advantages

* Short and simple code
* Less boilerplate

---

## Disadvantages

* Harder to test
* Breaks encapsulation
* Cannot use `final` fields
* Not recommended for production code

---

# 9. Comparison of Injection Types

| Injection Type        | Advantages                       | Disadvantages                 |
|-----------------------|----------------------------------|-------------------------------|
| Constructor Injection | Immutable, testable, recommended | Slightly more code            |
| Setter Injection      | Supports optional dependencies   | Dependencies can change       |
| Field Injection       | Simple syntax                    | Hard to test, not recommended |

---

# 10. Best Practice: Constructor Injection

Constructor Injection is the **recommended approach in Spring Boot**.

Reasons:

* Ensures required dependencies are always provided
* Makes classes immutable
* Improves testability
* Avoids `@Autowired` in most cases

Example:

```java
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
```

---

# 11. Lombok with Constructor Injection

Using **Lombok** can simplify constructor injection.

Example:

```java
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

}
```

`@RequiredArgsConstructor` automatically generates a constructor for `final` fields.

---

# 12. Dependency Injection Flow in Spring

When the application starts:

```text
1. Spring Boot starts
2. ApplicationContext is created
3. Beans are detected using component scanning
4. Spring creates bean objects
5. Dependencies are injected
6. Application becomes ready
```

Flow diagram:

```text
Spring Container
      ↓
Create Beans
      ↓
Inject Dependencies
      ↓
Application Ready
```

---

# 13. Summary

Dependency Injection is a core concept of the Spring Framework that allows objects to receive their dependencies from the Spring container.

Key points:

* IoC transfers control of object creation to Spring
* Dependency Injection reduces tight coupling
* Spring supports Constructor, Setter, and Field Injection
* Constructor Injection is the recommended approach

Using Dependency Injection helps build **clean, modular, and testable applications**.

---
