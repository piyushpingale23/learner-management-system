# 12 — RequestBody & ResponseEntity

## 1. Introduction

In Spring Boot REST APIs, data is usually exchanged in **JSON format** between the **client** and the **server**.

Spring provides several annotations and classes to handle request and response data.

Important components:

```text
@RequestBody
@ResponseBody
ResponseEntity
HttpStatus
```

These help in:

* Receiving request data from the client
* Sending responses back to the client
* Controlling HTTP status codes

---

# 2. @RequestBody

`@RequestBody` is used to **map the HTTP request body to a Java object**.

When a client sends JSON data, Spring automatically converts that JSON into a **Java object** using **Jackson**.

---

## Example Request JSON

```json
{
  "name": "Laptop",
  "price": 50000
}
```

---

## Example Java Model

```java
public class Product {

    private String name;
    private double price;

    // getters and setters

}
```

---

## Example Controller

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        return "Product Created: " + product.getName();
    }

}
```

Example request:

```text
POST /products
```

Request Body:

```json
{
  "name": "Laptop",
  "price": 50000
}
```

Spring automatically converts JSON → Java Object.

---

# 3. How @RequestBody Works

Process flow:

```text
Client Request (JSON)
        ↓
Spring DispatcherServlet
        ↓
Jackson converts JSON → Java Object
        ↓
Controller Method receives Object
```

Example method parameter:

```
@PostMapping
public String saveUser(@RequestBody User user)
```

Here:

```text
JSON → User Object
```

---

# 4. @ResponseBody

`@ResponseBody` is used to **convert a Java object into JSON** and send it in the HTTP response.

Example:

```java
@Controller
public class ProductController {

    @GetMapping("/product")
    @ResponseBody
    public Product getProduct() {

        return new Product("Laptop", 50000);

    }

}
```

Response:

```json
{
  "name": "Laptop",
  "price": 50000
}
```

---

## Relationship with @RestController

`@RestController` automatically includes:

```text
@Controller + @ResponseBody
```

So this:

```java
@RestController
public class ProductController {

}
```

is equivalent to:

```java
@Controller
@ResponseBody
public class ProductController {

}
```

Therefore, when using `@RestController`, you usually **do not need to write @ResponseBody**.

---

# 5. ResponseEntity

`ResponseEntity` is a class used to **customize the HTTP response**.

It allows you to control:

* Response body
* HTTP status code
* HTTP headers

Example syntax:

```
ResponseEntity<T>
```

Where `T` is the response body type.

---

## Basic Example

```java
@GetMapping("/product")
public ResponseEntity<Product> getProduct() {

    Product product = new Product("Laptop", 50000);

    return ResponseEntity.ok(product);

}
```

Response:

```text
Status: 200 OK
Body: Product JSON
```

---

# 6. ResponseEntity with Status Code

Example:

```java
@PostMapping
public ResponseEntity<String> createProduct() {

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Product Created");

}
```

Response:

```text
Status: 201 CREATED
Body: Product Created
```

---

# 7. ResponseEntity with Object

Example:

```java
@GetMapping("/{id}")
public ResponseEntity<Product> getProduct(@PathVariable Long id) {

    Product product = new Product("Laptop", 50000);

    return ResponseEntity.ok(product);

}
```

Response:

```json
{
  "name": "Laptop",
  "price": 50000
}
```

Status Code:

```text
200 OK
```

---

# 8. HttpStatus

`HttpStatus` is an **enum** provided by Spring that represents HTTP status codes.

Example usage:

```
HttpStatus.OK
HttpStatus.CREATED
HttpStatus.BAD_REQUEST
HttpStatus.NOT_FOUND
HttpStatus.INTERNAL_SERVER_ERROR
```

Example:

```
return ResponseEntity.status(HttpStatus.OK).body("Success");
```

---

# 9. Common HTTP Status Codes

| Status Code | Meaning               |
|-------------|-----------------------|
| 200         | OK                    |
| 201         | Created               |
| 400         | Bad Request           |
| 404         | Not Found             |
| 500         | Internal Server Error |

Example:

```
return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("User Not Found");
```

---

# 10. Complete Example

Controller example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Created");

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        User user = new User(id, "John");

        return ResponseEntity.ok(user);

    }

}
```

---

# 11. Flow of Request & Response

```text
Client sends JSON Request
        ↓
@RequestBody converts JSON → Java Object
        ↓
Controller processes request
        ↓
@ResponseBody converts Java Object → JSON
        ↓
ResponseEntity sends response with status code
```

---

# 12. Summary

Spring Boot provides powerful tools to handle HTTP requests and responses.

Key annotations and classes:

| Component        | Purpose                             |
|------------------|-------------------------------------|
| `@RequestBody`   | Convert JSON request → Java object  |
| `@ResponseBody`  | Convert Java object → JSON response |
| `ResponseEntity` | Customize HTTP response             |
| `HttpStatus`     | Represent HTTP status codes         |

Key points:

* `@RequestBody` is used to read request data
* `@ResponseBody` sends object data as JSON
* `ResponseEntity` gives full control over the response
* `HttpStatus` represents standard HTTP codes

These components are **essential for building REST APIs in Spring Boot**.

---
