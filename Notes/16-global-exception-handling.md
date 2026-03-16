# 16 — Global Exception Handling

## 1. Introduction

In Spring Boot applications, exceptions can occur during:

* API request processing
* Business logic execution
* Database operations
* Validation failures

Handling exceptions properly is important to:

* Provide meaningful error responses
* Avoid exposing internal errors
* Maintain clean controller code

Spring Boot provides **Global Exception Handling** using:

```text
@ControllerAdvice
@ExceptionHandler
```

This allows handling all exceptions in **one centralized class**.

---

# 2. Problems Without Global Exception Handling

Example controller:

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {

    return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

}
```

If the user is not found:

```text
java.lang.RuntimeException: User not found
```

Spring returns a **default error response**:

```json
{
  "timestamp": "2024-01-01T10:00:00",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/users/1"
}
```

Problems:

* Not user-friendly
* Hard to maintain
* Controllers become cluttered with try-catch blocks

Global exception handling solves this.

---

# 3. @ControllerAdvice

`@ControllerAdvice` is used to create a **global exception handler class**.

It applies to **all controllers in the application**.

Example:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

}
```

This class will intercept exceptions thrown from controllers.

---

# 4. @ExceptionHandler

`@ExceptionHandler` is used to define methods that handle specific exceptions.

Example:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());

    }

}
```

Now, whenever any exception occurs, this method will handle it.

---

# 5. Handling Specific Exceptions

Instead of handling all exceptions, it is better to handle **specific exceptions**.

Example:

```java
@ExceptionHandler(NullPointerException.class)
public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Null value encountered");

}
```

---

# 6. Custom Exceptions

Applications often define **custom exceptions** to represent specific business errors.

Example:

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
```

---

## Throwing Custom Exception

Example service:

```java
public User getUser(Long id) {

    return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

}
```

---

# 7. Handling Custom Exceptions

Global handler:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }

}
```

Response:

```json
{
  "message": "User not found"
}
```

HTTP Status:

```text
404 NOT FOUND
```

---

# 8. Returning Structured Error Response

Instead of returning plain strings, APIs should return structured responses.

Example error response class:

```java
public class ErrorResponse {

    private String message;
    private int status;
    private String timestamp;

}
```

Example handler:

```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {

    ErrorResponse error = new ErrorResponse();
    error.setMessage(ex.getMessage());
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setTimestamp(LocalDateTime.now().toString());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

}
```

Response:

```json
{
  "message": "User not found",
  "status": 404,
  "timestamp": "2024-01-01T10:10:00"
}
```

---

# 9. Handling Database Exceptions

Database operations may throw exceptions such as:

* `DataIntegrityViolationException`
* `SQLException`
* `EntityNotFoundException`

Example:

```java
@ExceptionHandler(DataIntegrityViolationException.class)
public ResponseEntity<String> handleDatabaseException(DataIntegrityViolationException ex) {

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Database constraint violation");

}
```

Example scenario:

```text
Duplicate email insertion
Foreign key constraint violation
```

---

# 10. Multiple Exception Handlers

A single global handler class can manage many exceptions.

Example:

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDatabaseError(DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Database Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }

}
```

---

# 11. Exception Handling Flow

```text
Client Request
       ↓
Controller
       ↓
Service
       ↓
Exception Thrown
       ↓
@ControllerAdvice Intercepts
       ↓
@ExceptionHandler Method Executes
       ↓
ResponseEntity Returned
       ↓
Client Receives Error Response
```

---

# 12. Best Practices

Recommended practices for exception handling:

* Use **custom exceptions for business errors**
* Handle exceptions **globally using @ControllerAdvice**
* Return **structured error responses**
* Avoid excessive try-catch in controllers
* Use proper HTTP status codes

Example folder structure:

```text
exception
│
├── GlobalExceptionHandler
├── ErrorResponse
└── UserNotFoundException
```

---

# 13. Summary

Global Exception Handling centralizes error management in Spring Boot applications.

Key components:

| Component           | Purpose                     |
|---------------------|-----------------------------|
| `@ControllerAdvice` | Global exception handler    |
| `@ExceptionHandler` | Handles specific exceptions |
| `Custom Exceptions` | Represent business errors   |
| `ResponseEntity`    | Return structured responses |

Benefits:

* Cleaner controllers
* Consistent API error responses
* Better debugging and maintainability
* Proper HTTP status codes

Global exception handling is **essential for building production-ready REST APIs** in Spring Boot.

---
