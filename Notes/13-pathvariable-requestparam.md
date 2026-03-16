# 13 — PathVariable & RequestParam

## 1. Introduction

When building REST APIs in Spring Boot, clients often send data through:

* **URL path**
* **Query parameters**

Spring provides two important annotations to extract these values from HTTP requests:

```text
@PathVariable
@RequestParam
```

These annotations allow controller methods to **receive values from the request URL**.

Example request:

```text
GET /users/10
GET /users?id=10
```

Both requests send the **user id**, but in different formats.

---

# 2. Path Variables

A **Path Variable** is a value embedded inside the **URL path**.

Example:

```text
/users/10
```

Here:

```text
10 → Path Variable
```

---

# 3. @PathVariable Annotation

`@PathVariable` is used to extract values from the **URI path**.

Example endpoint:

```text
GET /users/10
```

Controller example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "User ID: " + id;
    }

}
```

Request:

```text
GET http://localhost:8080/users/10
```

Response:

```text
User ID: 10
```

---

# 4. Multiple Path Variables

Spring supports multiple path variables in the URL.

Example URL:

```text
/orders/10/products/5
```

Controller example:

```java
@GetMapping("/orders/{orderId}/products/{productId}")
public String getOrderProduct(@PathVariable Long orderId,
                              @PathVariable Long productId) {

    return "Order: " + orderId + " Product: " + productId;

}
```

Request:

```text
GET /orders/10/products/5
```

Response:

```text
Order: 10 Product: 5
```

---

# 5. Named Path Variables

Sometimes the variable name in the URL may differ from the method parameter.

Example:

```java
@GetMapping("/users/{userId}")
public String getUser(@PathVariable("userId") Long id) {
    return "User ID: " + id;
}
```

Here:

```text
URL variable → userId
Method parameter → id
```

---

# 6. Query Parameters

Query parameters are **key-value pairs added after the URL using `?`**.

Example:

```text
/users?id=10
```

Or multiple parameters:

```text
/users?id=10&name=John
```

Structure:

```text
?key=value&key=value
```

---

# 7. @RequestParam Annotation

`@RequestParam` is used to extract **query parameters from the request URL**.

Example endpoint:

```text
GET /users?id=10
```

Controller example:

```java
@GetMapping("/users")
public String getUser(@RequestParam Long id) {
    return "User ID: " + id;
}
```

Request:

```text
GET http://localhost:8080/users?id=10
```

Response:

```text
User ID: 10
```

---

# 8. Multiple Query Parameters

Example request:

```text
GET /users?id=10&name=John
```

Controller:

```java
@GetMapping("/users")
public String getUser(@RequestParam Long id,
                      @RequestParam String name) {

    return "User: " + id + " " + name;

}
```

Response:

```text
User: 10 John
```

---

# 9. Optional Query Parameters

Query parameters can be optional.

Example:

```java
@GetMapping("/users")
public String getUser(@RequestParam(required = false) String name) {

    return "User Name: " + name;

}
```

Request:

```text
GET /users
```

Response:

```text
User Name: null
```

---

# 10. Default Values

Spring allows defining default values.

Example:

```java
@GetMapping("/users")
public String getUsers(@RequestParam(defaultValue = "Guest") String name) {

    return "User: " + name;

}
```

Request:

```text
GET /users
```

Response:

```text
User: Guest
```

---

# 11. PathVariable vs RequestParam

| Feature  | @PathVariable           | @RequestParam              |
|----------|-------------------------|----------------------------|
| Location | URL Path                | Query Parameter            |
| Example  | /users/10               | /users?id=10               |
| Usage    | Resource identification | Filtering or optional data |

Example comparison:

```text
/users/10
/users?id=10
```

---

# 12. API Design Best Practices

Good REST API design uses these annotations appropriately.

### Use Path Variables For

Resource identification.

Example:

```text
/users/10
/orders/20
/products/5
```

Controller:

```
@GetMapping("/users/{id}")
```

---

### Use RequestParam For

Filtering or searching.

Example:

```text
/products?category=electronics
/products?price=50000
```

Controller:

```
@GetMapping("/products")
public String getProducts(@RequestParam String category)
```

---

# 13. Real REST API Example

Example endpoints:

| Endpoint             | Purpose              |
|----------------------|----------------------|
| GET /users/10        | Get user by ID       |
| GET /users?name=John | Search users by name |

Controller example:

```
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "User ID: " + id;
    }

    @GetMapping
    public String searchUser(@RequestParam String name) {
        return "User Name: " + name;
    }

}
```

---

# 14. Summary

Spring Boot provides powerful annotations to extract data from request URLs.

Important annotations:

| Annotation      | Purpose                     |
|-----------------|-----------------------------|
| `@PathVariable` | Extract value from URL path |
| `@RequestParam` | Extract query parameters    |

Key differences:

```text
@PathVariable → Resource identification
@RequestParam → Filtering or additional data
```

Using these annotations correctly helps design **clean, readable, and RESTful APIs**.

---
