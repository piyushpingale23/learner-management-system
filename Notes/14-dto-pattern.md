# 14 — DTO Pattern

## 1. Introduction

**DTO** stands for **Data Transfer Object**.

A DTO is a **simple object used to transfer data between layers of an application**.

DTOs are commonly used in **Spring Boot REST APIs** to:

* Transfer request data from **client → controller**
* Transfer response data from **server → client**

DTOs usually contain:

* Fields
* Getters and setters
* No business logic

Example DTO:

```java
public class UserDTO {

    private String name;
    private String email;

    // getters and setters

}
```

DTOs are used mainly in **Controller and Service layers**.

---

# 2. Why DTO is Used

Using DTOs is considered a **best practice in API development**.

Main reasons:

### 1. Security

Entities often contain **sensitive fields** that should not be exposed to clients.

Example Entity:

```java
@Entity
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;

}
```

If we return this entity directly from the API:

```java
@GetMapping("/users")
public User getUser() {
    return user;
}
```

Response:

```json
{
  "id": 1,
  "name": "John",
  "email": "john@gmail.com",
  "password": "123456"
}
```

This exposes the **password**, which is a security risk.

Using DTO solves this problem.

Example DTO:

```java
public class UserDTO {

    private String name;
    private String email;

}
```

Response:

```json
{
  "name": "John",
  "email": "john@gmail.com"
}
```

Sensitive data is hidden.

---

### 2. Separation of Layers

DTO helps separate:

```text
Database Layer (Entity)
        ↓
Service Layer
        ↓
Controller Layer (DTO)
```

This ensures that **database models are not tightly coupled with API models**.

---

### 3. API Stability

If database structure changes, API responses remain unaffected.

Example:

Database change:

```java
private String phoneNumber;
```

DTO does not need to expose this field.

---

### 4. Better Validation

DTOs are the best place to apply **validation annotations**.

Example:

```java
public class UserDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

}
```

This validates incoming request data before it reaches the business logic.

---

# 3. DTO Flow in Spring Boot

Typical request flow:

```text
Client Request (JSON)
        ↓
@RequestBody
        ↓
DTO Object
        ↓
Service Layer
        ↓
Entity
        ↓
Database
```

Response flow:

```text
Database Entity
        ↓
Service Layer
        ↓
DTO
        ↓
JSON Response
        ↓
Client
```

---

# 4. Example Without DTO (Bad Practice)

Example controller:

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {

    return userRepository.save(user);

}
```

Problems:

* Exposes database structure
* Security risks
* Hard to maintain

---

# 5. Example With DTO (Best Practice)

### DTO Class

```java
public class UserDTO {

    private String name;
    private String email;

}
```

---

### Entity Class

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private String password;

}
```

---

### Controller

```java
@PostMapping("/users")
public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());

    userRepository.save(user);

    return ResponseEntity.ok(userDTO);

}
```

Here:

```text
Client → DTO → Entity → Database
```

---

# 6. DTO vs Entity

| Feature                 | DTO                    | Entity                |
|-------------------------|------------------------|-----------------------|
| Purpose                 | Data transfer          | Database mapping      |
| Layer                   | Controller / Service   | Repository / Database |
| Annotations             | Validation annotations | JPA annotations       |
| Contains business logic | No                     | Sometimes             |
| Database table mapping  | No                     | Yes                   |

---

## Entity Example

```java
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
```

Entities represent **database tables**.

---

## DTO Example

```java
public class ProductDTO {

    private String name;

}
```

DTO represents **API request/response data**.

---

# 7. Manual DTO Mapping

DTO and Entity must be converted.

Example:

```
User user = new User();
user.setName(userDTO.getName());
user.setEmail(userDTO.getEmail());
```

This is called **DTO Mapping**.

---

# 8. DTO Mapping Tools

Large projects use libraries to automate DTO mapping.

Common tools:

| Tool        | Purpose              |
|-------------|----------------------|
| MapStruct   | Compile-time mapping |
| ModelMapper | Runtime mapping      |

Example using ModelMapper:

```java
User user = modelMapper.map(userDTO, User.class);
```

---

# 9. Best Practices for DTO

Recommended practices:

* Never expose entities directly in APIs
* Use DTOs for request and response
* Apply validation annotations on DTO
* Use mapping libraries for large projects

Typical structure:

```text
controller
service
repository
entity
dto
```

Example:

```text
com.example.project
│
├── controller
├── service
├── repository
├── entity
└── dto
```

---

# 10. Summary

DTO (Data Transfer Object) is a design pattern used to transfer data between application layers.

Key benefits:

* Improves security
* Separates database models from API models
* Provides better validation
* Makes APIs stable and maintainable

Main difference:

```text
Entity → Database representation
DTO → API data representation
```

Using DTOs is considered a **best practice for building clean and secure Spring Boot APIs**.

---
