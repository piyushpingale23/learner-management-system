# 10 — REST API Development

## 1. Introduction

**REST API** stands for **Representational State Transfer Application Programming Interface**.

REST is an **architectural style** used for building **web services** that communicate over **HTTP**.

In REST architecture:

* The **client** sends a request
* The **server** processes it
* The **server returns a response**, usually in **JSON format**

Spring Boot makes it very easy to develop REST APIs using annotations such as:

```text
@RestController
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
```

---

# 2. What is REST?

REST is a **stateless architecture** used for designing network applications.

It uses **standard HTTP methods** to perform operations on resources.

Example resource:

```text
/users
/products
/orders
```

Example API request:

```text
GET /users
```

Response:

```json
[
  {
    "id": 1,
    "name": "John"
  }
]
```

Here:

* `/users` → Resource
* `GET` → Operation on resource

---

# 3. REST Principles

REST architecture follows several principles.

## 1. Client–Server Architecture

The client and server are **independent systems**.

Example:

* Client → Browser / Mobile App
* Server → Spring Boot Application

---

## 2. Stateless

Each request must contain **all necessary information**.

The server **does not store client state between requests**.

Example:

```text
GET /users/1
```

The server processes the request independently.

---

## 3. Resource-Based

REST APIs revolve around **resources**.

Example resources:

```text
/users
/orders
/products
```

Each resource is identified using a **URI**.

Example:

```text
/users/10
```

---

## 4. Uniform Interface

REST APIs follow **standard HTTP methods**.

Example:

| Method | Operation     |
|--------|---------------|
| GET    | Retrieve data |
| POST   | Create data   |
| PUT    | Update data   |
| DELETE | Remove data   |

---

## 5. Representation of Resources

Resources are usually represented in:

* JSON (most common)
* XML

Example JSON:

```json
{
  "id": 1,
  "name": "Laptop"
}
```

---

# 4. HTTP Methods

REST APIs use **HTTP methods** to perform operations on resources.

Main methods:

```text
GET
POST
PUT
DELETE
PATCH
```

---

# 5. GET Method

The **GET method** is used to **retrieve data** from the server.

Example:

```text
GET /users
```

Response:

```json
[
  {
    "id": 1,
    "name": "John"
  }
]
```

Example Spring Boot controller:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers() {
        return "All Users";
    }

}
```

Example request:

```text
GET http://localhost:8080/users
```

---

# 6. POST Method

The **POST method** is used to **create new resources**.

Example request:

```text
POST /users
```

Request body:

```json
{
  "name": "John"
}
```

Spring Boot example:

```java
@PostMapping
public String createUser() {
    return "User Created";
}
```

Typical response:

```text
201 Created
```

---

# 7. PUT Method

The **PUT method** is used to **update an existing resource**.

PUT usually updates the **entire resource**.

Example:

```text
PUT /users/1
```

Request body:

```json
{
  "id": 1,
  "name": "Updated User"
}
```

Spring Boot example:

```java
@PutMapping("/{id}")
public String updateUser(@PathVariable Long id) {
    return "User Updated";
}
```

---

# 8. DELETE Method

The **DELETE method** is used to **remove a resource**.

Example request:

```text
DELETE /users/1
```

Spring Boot example:

```java
@DeleteMapping("/{id}")
public String deleteUser(@PathVariable Long id) {
    return "User Deleted";
}
```

---

# 9. PATCH Method

The **PATCH method** is used to **partially update a resource**.

Unlike PUT, PATCH updates **only specific fields**.

Example request:

```text
PATCH /users/1
```

Request body:

```json
{
  "name": "Updated Name"
}
```

Example controller:

```java
@PatchMapping("/{id}")
public String updateUserName(@PathVariable Long id) {
    return "User Name Updated";
}
```

---

# 10. REST API Example

Example resource: **Products**

API endpoints:

| Method | Endpoint       | Description       |
|--------|----------------|-------------------|
| GET    | /products      | Get all products  |
| GET    | /products/{id} | Get product by id |
| POST   | /products      | Create product    |
| PUT    | /products/{id} | Update product    |
| DELETE | /products/{id} | Delete product    |

Example controller:

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

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Product Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Product Deleted";
    }

}
```

---

# 11. HTTP Status Codes

REST APIs return **HTTP status codes** to indicate the result.

Common status codes:

| Code | Meaning               |
|------|-----------------------|
| 200  | OK                    |
| 201  | Created               |
| 400  | Bad Request           |
| 404  | Not Found             |
| 500  | Internal Server Error |

Example:

```
return ResponseEntity.status(HttpStatus.CREATED).body(product);
```

---

# 12. Summary

REST API is an architectural style used to build web services using HTTP.

Key points:

* REST uses **HTTP protocols**
* APIs operate on **resources**
* Communication is usually **JSON-based**
* Uses standard HTTP methods

Main HTTP methods:

```text
GET    → Retrieve data
POST   → Create resource
PUT    → Update resource
PATCH  → Partial update
DELETE → Remove resource
```

Spring Boot provides powerful annotations that make REST API development **simple and efficient**.

---
