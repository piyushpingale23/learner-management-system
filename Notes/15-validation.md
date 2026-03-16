# Spring Boot Bean Validation (Jakarta Validation)

## 1. What is Validation?

Validation ensures that incoming data follows certain rules before it is processed by the application.

Example:

* Name should not be null
* Email must be valid
* Password must have minimum length

Spring Boot uses **Jakarta Bean Validation** with **Hibernate Validator** implementation.

---

# 2. Validation Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

This dependency provides:

* Jakarta Validation API
* Hibernate Validator implementation

---

# 3. Why @Valid or @Validated is Required

Annotations like:

* `@NotNull`
* `@Size`
* `@Email`

only define rules.

Validation is executed **only when triggered using:**

* `@Valid`
* `@Validated`

Example:

```java
@PostMapping("/users")
public ResponseEntity<?> createUser(@Valid @RequestBody User user){
    return ResponseEntity.ok("User created");
}
```

If validation fails:

* Spring throws `MethodArgumentNotValidException`
* Response → **400 Bad Request**

---

# 4. Common Validation Annotations

## 4.1 Null Validations

### @NotNull

Field must not be null.

```java
@NotNull(message = "Name cannot be null")
private String name;
```

---

### @NotEmpty

Field must not be null or empty.

Works for:

* String
* Collection
* Map
* Array

```java
@NotEmpty
private String username;
```

---

### @NotBlank

Used for **String only**.

Rules:

* Not null
* Not empty
* Cannot contain only whitespace

```java
@NotBlank
private String name;
```

---

# 5. Size Validations

### @Size

Defines minimum and maximum length.

```java
@Size(min = 3, max = 20)
private String username;
```

Works for:

* String
* Collection
* Array
* Map

---

# 6. Numeric Validations

### @Min

Minimum value.

```java
@Min(18)
private int age;
```

---

### @Max

Maximum value.

```java
@Max(60)
private int age;
```

---

### @Positive

Value must be positive.

```java
@Positive
private int quantity;
```

---

### @PositiveOrZero

Value must be positive or zero.

```java
@PositiveOrZero
private int stock;
```

---

### @Negative

Value must be negative.

```java
@Negative
private int temperature;
```

---

### @NegativeOrZero

Value must be negative or zero.

```java
@NegativeOrZero
private int value;
```

---

# 7. String Format Validations

### @Email

Validates email format.

```java
@Email(message = "Invalid email format")
private String email;
```

---

### @Pattern

Validates using regular expression.

Example:

```java
@Pattern(regexp = "^(10|[1-9])C$")
private String reconCycle;
```

---

# 8. Date Validations

### @Past

Date must be in the past.

```java
@Past
private LocalDate birthDate;
```

---

### @Future

Date must be in the future.

```java
@Future
private LocalDate expiryDate;
```

---

### @PastOrPresent

```java
@PastOrPresent
private LocalDate createdDate;
```

---

### @FutureOrPresent

```java
@FutureOrPresent
private LocalDate startDate;
```

---

# 9. Boolean Validation

### @AssertTrue

Field must be true.

```java
@AssertTrue
private boolean active;
```

---

### @AssertFalse

Field must be false.

```java
@AssertFalse
private boolean deleted;
```

---

# 10. Nested Object Validation

If a DTO contains another object, use `@Valid`.

Example:

```java
public class Order {

    @Valid
    private Customer customer;
}
```

Without `@Valid`, nested validation **will not run**.

---

# 11. Example DTO

```java
public class UserDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Size(min = 6, max = 20)
    private String password;

    @Min(18)
    private int age;
}
```

---

# 12. Controller Example

```java
@PostMapping("/users")
public ResponseEntity<?> createUser(
        @Valid @RequestBody UserDTO userDTO) {

    return ResponseEntity.ok("User created");
}
```

---

# 13. Global Exception Handler

Handle validation errors properly.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException ex){

        String error = ex.getBindingResult()
                         .getAllErrors()
                         .get(0)
                         .getDefaultMessage();

        return ResponseEntity.badRequest().body(error);
    }
}
```

---

# 14. @Valid vs @Validated

| Feature        | @Valid             | @Validated        |
|----------------|--------------------|-------------------|
| Package        | jakarta.validation | springframework   |
| Groups support | No                 | Yes               |
| Mostly used    | DTO validation     | Method validation |

Example:

```java
@Validated
@RestController
public class UserController {
}
```

---

# 15. Method Parameter Validation

Example:

```java
@GetMapping("/users")
public String getUser(@RequestParam @Min(1) int id){
    return "User";
}
```

Controller must have:

```
@Validated
@RestController
```

---

# 16. Validation Flow in Spring Boot

```
Client Request
      ↓
DispatcherServlet
      ↓
HandlerMethodArgumentResolver
      ↓
Bean Validation (@Valid)
      ↓
Validation Fail → MethodArgumentNotValidException
      ↓
ControllerAdvice
      ↓
HTTP 400 Response
```

---

# 17. Important Rules

1. Validation annotations only define rules.
2. `@Valid` or `@Validated` triggers validation.
3. Without them, validation **will not execute**.
4. Use `@ControllerAdvice` to handle validation errors.
5. Use `@Valid` for nested object validation.

---

# 18. Quick Summary

| Annotation | Purpose                 |
|------------|-------------------------|
| @NotNull   | Field cannot be null    |
| @NotEmpty  | Cannot be null or empty |
| @NotBlank  | String cannot be blank  |
| @Size      | Define length           |
| @Email     | Email validation        |
| @Pattern   | Regex validation        |
| @Min       | Minimum value           |
| @Max       | Maximum value           |
| @Positive  | Positive numbers        |
| @Negative  | Negative numbers        |
| @Past      | Past date               |
| @Future    | Future date             |

---
