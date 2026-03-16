# 18 — Entity Annotations

## 1. Introduction

In **Spring Data JPA**, database tables are mapped to Java classes using **JPA Entity annotations**.

These annotations help define how a **Java object corresponds to a database table**.

Basic mapping concept:

```
Database Table  →  Java Class
Table Row       →  Java Object
Table Column    →  Class Field
```

Example:

Database table:

```
users
------------------------
id | name | email
------------------------
```

Java entity:

```java
@Entity
public class User {

    @Id
    private Long id;

    private String name;

    private String email;

}
```

Spring Data JPA uses these annotations to automatically map the object to the database.

---

# 2. @Entity

`@Entity` marks a class as a **JPA entity**.

An entity represents a **database table**.

Example:

```java
import jakarta.persistence.Entity;

@Entity
public class Product {

    private Long id;
    private String name;

}
```

When Spring Boot starts, Hibernate will map this class to a database table.

Default table name:

```
Product
```

### Rules for Entity Classes

An entity class should:

* Be a **POJO class**
* Have a **primary key**
* Have a **default constructor**
* Not be final

Example:

```java
@Entity
public class User {

    public User() {}

}
```

---

# 3. @Table

`@Table` specifies the **database table name**.

If not provided, Hibernate uses the **class name as table name**.

Example:

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

}
```

Now the table name will be:

```
users
```

Instead of:

```
User
```

### Additional Properties

`@Table` can also define:

* unique constraints
* indexes
* schema

Example:

```
@Table(
    name = "users",
    schema = "public"
)
```

---

# 4. @Id

`@Id` marks a field as the **Primary Key** of the table.

Every entity must have **one primary key**.

Example:

```java
@Entity
public class User {

    @Id
    private Long id;

}
```

Database table:

```
users
-------------
id (PK)
name
email
```

Primary key properties:

* Unique
* Not null
* Identifies each record

---

# 5. @GeneratedValue

`@GeneratedValue` automatically generates the **primary key value**.

It is usually used with `@Id`.

Example:

```java
@Id
@GeneratedValue
private Long id;
```

This means the database will generate the ID automatically.

### Generation Strategies

| Strategy | Description                  |
|----------|------------------------------|
| AUTO     | Default strategy             |
| IDENTITY | Uses database auto-increment |
| SEQUENCE | Uses database sequence       |
| TABLE    | Uses a separate table        |

Example:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

Database example:

```
id | name
---------
1  | John
2  | Alex
3  | Sara
```

The ID increases automatically.

---

# 6. @Column

`@Column` maps a class field to a **specific database column**.

If not specified, Hibernate uses the **field name as column name**.

Example:

```java
@Column(name = "user_name")
private String name;
```

Database column:

```
user_name
```

Instead of:

```
name
```

---

### Common @Column Attributes

| Attribute | Purpose                       |
|-----------|-------------------------------|
| name      | Column name                   |
| nullable  | Whether column allows null    |
| unique    | Unique constraint             |
| length    | String length                 |
| updatable | Whether column can be updated |

Example:

```java
@Column(
    name = "email",
    nullable = false,
    unique = true,
    length = 100
)
private String email;
```

Database column:

```
email VARCHAR(100) NOT NULL UNIQUE
```

---

# 7. Complete Entity Example

Example entity with all annotations:

```java
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

}
```

Database table:

```
users
--------------------------------
id | user_name | email
--------------------------------
1  | John      | john@gmail.com
```

---

# 8. Entity Lifecycle (Basic)

When working with JPA entities, the lifecycle typically follows:

```
Create Entity Object
       ↓
Save using Repository
       ↓
Hibernate converts to SQL
       ↓
Data stored in database
```

Example:

```
User user = new User();
user.setName("John");
user.setEmail("john@gmail.com");

userRepository.save(user);
```

Hibernate internally executes SQL:

```
INSERT INTO users (user_name, email)
VALUES ('John','john@gmail.com');
```

---

# 9. Best Practices

Recommended practices when creating entities:

* Always define a **primary key**
* Use `GenerationType.IDENTITY` for MySQL
* Avoid exposing entities directly in APIs
* Use **DTO pattern** for API communication
* Keep entities focused on database mapping

Typical project structure:

```
entity
dto
repository
service
controller
```

---

# 10. Summary

Entity annotations define how Java classes map to database tables.

Key annotations:

| Annotation        | Purpose                       |
|-------------------|-------------------------------|
| `@Entity`         | Marks class as database table |
| `@Table`          | Specifies table name          |
| `@Id`             | Primary key                   |
| `@GeneratedValue` | Auto-generates primary key    |
| `@Column`         | Maps field to column          |

Example mapping:

```
@Entity class  →  Database table
Field          →  Column
@Id            →  Primary Key
```

These annotations are fundamental for building **database-driven applications using Spring Data JPA**.

---
