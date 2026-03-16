# 20 — Repository Layer

## 1. Introduction

In **Spring Boot**, the **Repository Layer** is responsible for interacting with the database.

It sits between:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

The repository layer handles:

* Saving data
* Fetching data
* Updating records
* Deleting records

Spring Boot provides **Spring Data JPA**, which simplifies repository creation by providing ready-made interfaces.

Main repository interfaces:

| Interface                    | Purpose                |
|------------------------------|------------------------|
| `CrudRepository`             | Basic CRUD operations  |
| `PagingAndSortingRepository` | Pagination and sorting |
| `JpaRepository`              | Full JPA functionality |

---

# 2. What is a Repository

A **Repository** is an interface that communicates with the database.

Instead of writing SQL queries, Spring Data JPA automatically generates implementations.

Example:

```java
public interface UserRepository extends JpaRepository<User, Long> {

}
```

Explanation:

| Part             | Meaning                   |
|------------------|---------------------------|
| `UserRepository` | Repository interface      |
| `JpaRepository`  | Spring Data JPA interface |
| `User`           | Entity class              |
| `Long`           | Primary key type          |

Spring automatically provides implementations for common database operations.

---

# 3. CrudRepository

`CrudRepository` provides **basic CRUD operations**.

CRUD stands for:

```text
Create
Read
Update
Delete
```

Interface:

```java
public interface CrudRepository<T, ID> extends Repository<T, ID>
{}
```

Where:

| Parameter | Meaning          |
|-----------|------------------|
| `T`       | Entity type      |
| `ID`      | Primary key type |

---

## Common Methods

| Method         | Description             |
|----------------|-------------------------|
| `save()`       | Insert or update entity |
| `findById()`   | Find record by ID       |
| `findAll()`    | Get all records         |
| `deleteById()` | Delete record           |
| `count()`      | Count total records     |
| `existsById()` | Check if record exists  |

Example:

```java
public interface UserRepository extends CrudRepository<User, Long> {

}
```

Usage:

```
userRepository.save(user);

userRepository.findById(1L);

userRepository.deleteById(1L);
```

---

# 4. PagingAndSortingRepository

`PagingAndSortingRepository` extends `CrudRepository`.

It adds support for:

* Pagination
* Sorting

Interface:

```java
public interface PagingAndSortingRepository<T, ID>
        extends CrudRepository<T, ID> {}
```

---

## Pagination Example

Pagination helps retrieve data in **pages instead of loading all data at once**.

Example:

```java
Pageable pageable = PageRequest.of(0, 10);
Page<User> users = userRepository.findAll(pageable);
```

Meaning:

```text
Page 0
10 records per page
```

---

## Sorting Example

Sorting allows ordering data.

Example:

```java
Sort sort = Sort.by("name").ascending();
Iterable<User> users = userRepository.findAll(sort);
```

Sort options:

```text
ascending()
descending()
```

---

# 5. JpaRepository

`JpaRepository` is the **most commonly used repository interface**.

It extends:

```text
PagingAndSortingRepository
        ↓
CrudRepository
```

Hierarchy:

```text
Repository
   ↓
CrudRepository
   ↓
PagingAndSortingRepository
   ↓
JpaRepository
```

JpaRepository provides **all CRUD operations plus additional JPA features**.

---

## Common JpaRepository Methods

| Method           | Description                     |
|------------------|---------------------------------|
| `save()`         | Save entity                     |
| `findAll()`      | Retrieve all records            |
| `findById()`     | Find by ID                      |
| `delete()`       | Delete entity                   |
| `flush()`        | Synchronize persistence context |
| `saveAndFlush()` | Save and flush immediately      |

Example:

```java
public interface UserRepository extends JpaRepository<User, Long> {

}
```

---

# 6. Example Repository

Example entity:

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
```

Repository:

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
```

Service usage:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
```

Spring Boot automatically generates the repository implementation at runtime.

---

# 7. Custom Query Methods

Spring Data JPA allows **query methods based on method names**.

Example:

```java
List<User> findByName(String name);
```

Spring automatically generates SQL:

```sql
SELECT * FROM users WHERE name = ?
```

More examples:

```java
findByEmail(String email) {}

findByNameAndEmail(String name, String email) {}

findByAgeGreaterThan(int age) {}
```

This feature is called **Query Method Derivation**.

---

# 8. Repository Injection

Repositories are typically injected into **Service classes**.

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

Using **Constructor Injection** is considered best practice.

---

# 9. Typical Project Structure

Example structure:

```text
com.example.project
│
├── controller
├── service
├── repository
├── entity
└── dto
```

Repository example:

```text
repository
   └── UserRepository.java
```

---

# 10. Comparison of Repository Interfaces

| Feature              | CrudRepository | PagingAndSortingRepository | JpaRepository |
|----------------------|----------------|----------------------------|---------------|
| CRUD operations      | Yes            | Yes                        | Yes           |
| Pagination           | No             | Yes                        | Yes           |
| Sorting              | No             | Yes                        | Yes           |
| JPA specific methods | No             | No                         | Yes           |
| Most commonly used   | No             | No                         | Yes           |

In most Spring Boot applications:

```text
JpaRepository is used.
```

---

# 11. Best Practices

Recommended practices:

* Use `JpaRepository` in most applications
* Keep repository interfaces simple
* Write custom queries only when necessary
* Use **constructor injection**
* Avoid business logic in repositories

Repository responsibility should remain:

```text
Database access only
```

---

# 12. Summary

The **Repository Layer** is responsible for database interaction in Spring Boot applications.

Key repository interfaces:

| Interface                    | Purpose                 |
|------------------------------|-------------------------|
| `CrudRepository`             | Basic CRUD operations   |
| `PagingAndSortingRepository` | Pagination and sorting  |
| `JpaRepository`              | Complete JPA repository |

Hierarchy:

```text
Repository
   ↓
CrudRepository
   ↓
PagingAndSortingRepository
   ↓
JpaRepository
```

Most real-world Spring Boot projects use:

```text
JpaRepository
```

because it provides the most functionality with minimal configuration.

---
