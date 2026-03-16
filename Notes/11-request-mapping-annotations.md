# 11 — Request Mapping Annotations

## 1. Introduction

In Spring Boot, **Request Mapping Annotations** are used to map **HTTP requests to controller methods**.

These annotations help the Spring framework determine **which method should handle a particular HTTP request**.

Spring Boot provides several request mapping annotations:

```text
@RequestMapping
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
```

These annotations are commonly used in **REST Controllers**.

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

}
```

This means all endpoints in this controller start with `/users`.

---

# 2. @RequestMapping

`@RequestMapping` is the **base annotation** used to map HTTP requests to handler methods.

It can be applied at:

* Class level
* Method level

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/all")
    public String getUsers() {
        return "All Users";
    }

}
```

Endpoint:

```text
GET /users/all
```

---

## Specifying HTTP Method

You can specify the HTTP method using `method`.

Example:

```java
@RequestMapping(value = "/users", method = RequestMethod.GET)
public String getUsers() {
    return "All Users";
}
```

However, this syntax is **verbose**, so Spring provides shortcut annotations.

---

# 3. @GetMapping

`@GetMapping` is used to handle **HTTP GET requests**.

GET is used to **retrieve data**.

Example:

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "All Products";
    }

}
```

Endpoint:

```text
GET /products
```

Example with path variable:

```java
@GetMapping("/{id}")
public String getProductById(@PathVariable Long id) {
    return "Product ID: " + id;
}
```

Endpoint:

```text
GET /products/10
```

---

# 4. @PostMapping

`@PostMapping` is used to handle **HTTP POST requests**.

POST is used to **create new resources**.

Example:

```java
@PostMapping
public String createProduct() {
    return "Product Created";
}
```

Endpoint:

```text
POST /products
```

Example with request body:

```java
@PostMapping
public String createProduct(@RequestBody Product product) {
    return "Product Saved";
}
```

Example request body:

```json
{
  "name": "Laptop",
  "price": 50000
}
```

---

# 5. @PutMapping

`@PutMapping` is used to **update an existing resource**.

PUT typically updates the **entire object**.

Example:

```java
@PutMapping("/{id}")
public String updateProduct(@PathVariable Long id) {
    return "Product Updated";
}
```

Endpoint:

```text
PUT /products/5
```

Example with request body:

```java
@PutMapping("/{id}")
public String updateProduct(@PathVariable Long id,
                            @RequestBody Product product) {
    return "Product Updated";
}
```

---

# 6. @DeleteMapping

`@DeleteMapping` is used to **delete a resource**.

Example:

```java
@DeleteMapping("/{id}")
public String deleteProduct(@PathVariable Long id) {
    return "Product Deleted";
}
```

Endpoint:

```text
DELETE /products/5
```

---

# 7. Class Level vs Method Level Mapping

Spring allows mapping at **two levels**.

## Class Level

Defines a **base URL**.

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

}
```

---

## Method Level

Defines the **specific endpoint**.

Example:

```java
@GetMapping("/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

Final endpoint:

```text
GET /users/1
```

---

# 8. Mapping Multiple Endpoints

Spring allows mapping multiple URLs to the same method.

Example:

```java
@GetMapping({"/all", "/list"})
public String getUsers() {
    return "User List";
}
```

Endpoints:

```text
GET /users/all
GET /users/list
```

---

# 9. Path Variables

Path variables are values inside the URL.

Example URL:

```text
/products/10
```

Controller:

```java
@GetMapping("/{id}")
public String getProduct(@PathVariable Long id) {
    return "Product ID: " + id;
}
```

Here:

```text
10 → PathVariable
```

---

# 10. Request Body

`@RequestBody` is used to map **JSON request data to Java objects**.

Example request:

```json
{
  "name": "Laptop",
  "price": 50000
}
```

Controller:

```java
@PostMapping
public String createProduct(@RequestBody Product product) {
    return "Product Created";
}
```

Spring automatically converts JSON into a Java object.

---

# 11. REST Controller Example

Complete example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All Users";
    }

    @PostMapping
    public String createUser() {
        return "User Created";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id) {
        return "User Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User Deleted";
    }

}
```

Endpoints:

```text
GET    /users
POST   /users
PUT    /users/{id}
DELETE /users/{id}
```

---

# 12. Summary

Request Mapping Annotations are used to map **HTTP requests to controller methods**.

Common annotations:

| Annotation        | HTTP Method | Purpose         |
|-------------------|-------------|-----------------|
| `@RequestMapping` | Any         | Generic mapping |
| `@GetMapping`     | GET         | Retrieve data   |
| `@PostMapping`    | POST        | Create resource |
| `@PutMapping`     | PUT         | Update resource |
| `@DeleteMapping`  | DELETE      | Delete resource |

Key points:

* Used in **Spring Controllers**
* Map URLs to Java methods
* Support path variables and request bodies
* Essential for building **REST APIs in Spring Boot**

---
