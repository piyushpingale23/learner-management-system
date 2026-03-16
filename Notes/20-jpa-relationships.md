# 21 — JPA Relationships

## 1. Introduction

In relational databases, tables are connected using **relationships**.

Example:

```text
Customer → Orders
Department → Employees
Student → Courses
```

In **JPA (Java Persistence API)**, relationships between entities are defined using special annotations.

Main relationship types:

| Relationship | Annotation    |
|--------------|---------------|
| One-to-One   | `@OneToOne`   |
| One-to-Many  | `@OneToMany`  |
| Many-to-One  | `@ManyToOne`  |
| Many-to-Many | `@ManyToMany` |

These annotations help map **object relationships to database foreign keys**.

---

# 2. One-to-One Relationship (`@OneToOne`)

A **One-to-One** relationship means:

```
One record in Table A is associated with exactly one record in Table B.
```

Example:

```
User → Passport
Person → Aadhaar
Employee → Locker
```

Database Example:

```
users
----------------
id | name
----------------

passport
---------------------------
id | passport_number | user_id
---------------------------
```

### Entity Example

User Entity:

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

}
```

Passport Entity:

```java
@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    private String passportNumber;

}
```

### Foreign Key

```
passport_id → foreign key
```

The `@JoinColumn` defines the **foreign key column**.

---

# 3. One-to-Many Relationship (`@OneToMany`)

A **One-to-Many** relationship means:

```
One entity can be associated with multiple entities.
```

Example:

```
Department → Employees
Customer → Orders
Author → Books
```

Database Example:

```
department
----------------
id | name
----------------

employee
----------------------------
id | name | department_id
----------------------------
```

### Entity Example

Department:

```java
@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

}
```

Employee:

```java
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
```

Here:

```
department_id → foreign key
```

---

# 4. Many-to-One Relationship (`@ManyToOne`)

A **Many-to-One** relationship means:

```
Many records belong to one record.
```

Example:

```
Many Employees → One Department
Many Orders → One Customer
Many Students → One Course
```

Database Example:

```
orders
-----------------------------
id | order_name | customer_id
-----------------------------
```

### Entity Example

Order Entity:

```java
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String orderName;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
```

Customer Entity:

```java
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
```

Here:

```
customer_id → foreign key
```

---

# 5. Many-to-Many Relationship (`@ManyToMany`)

A **Many-to-Many** relationship means:

```
Many records in Table A relate to many records in Table B.
```

Example:

```
Students → Courses
Users → Roles
Authors → Books
```

Database Example:

```
students
----------------
id | name
----------------

courses
----------------
id | title
----------------

student_courses
----------------------------
student_id | course_id
----------------------------
```

The **join table** connects both tables.

---

### Entity Example

Student Entity:

```java
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

}
```

Course Entity:

```java
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

}
```

Join Table:

```
student_courses
------------------------
student_id | course_id
```

---

# 6. Owning Side of Relationship

In JPA relationships, one side must be the **owning side**.

The owning side is responsible for:

```
Managing the relationship
Updating the foreign key
Persisting relationship changes
```

The owning side contains:

```
@JoinColumn
or
@JoinTable
```

---

## Example: One-to-Many Relationship

Department:

```java
@OneToMany(mappedBy = "department")
private List<Employee> employees;
```

Employee:

```java
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
```

### Owning Side

```
Employee entity
```

Because it contains:

```
@JoinColumn
```

### Inverse Side

```
Department entity
```

Because it contains:

```
mappedBy
```

---

# 7. mappedBy Attribute

`mappedBy` tells JPA:

```
This side is not the owner of the relationship.
```

Example:

```java
@OneToMany(mappedBy = "department")
private List<Employee> employees;
```

Meaning:

```
Employee entity manages the relationship.
```

The foreign key exists in:

```
employee table
```

---

# 8. Relationship Ownership Rules

| Relationship | Owning Side            |
|--------------|------------------------|
| One-to-One   | Side with foreign key  |
| One-to-Many  | Many side              |
| Many-to-One  | Many side              |
| Many-to-Many | Side with `@JoinTable` |

Example:

```
Department → Employees
```

Owner:

```
Employee (Many side)
```

Because it contains:

```
department_id
```

---

# 9. Best Practices

Recommended practices:

* Prefer **Many-to-One** instead of One-to-Many when possible
* Avoid unnecessary **bidirectional relationships**
* Use **DTOs for API responses**
* Avoid returning entity graphs directly in REST APIs
* Use `mappedBy` properly to avoid duplicate foreign keys

Typical structure:

```
entity
repository
service
controller
dto
```

---

# 10. Summary

JPA relationships allow entities to connect with each other.

Main annotations:

| Annotation    | Purpose                  |
|---------------|--------------------------|
| `@OneToOne`   | One record to one record |
| `@OneToMany`  | One record to many       |
| `@ManyToOne`  | Many records to one      |
| `@ManyToMany` | Many records to many     |

Supporting annotations:

| Annotation    | Purpose              |
|---------------|----------------------|
| `@JoinColumn` | Defines foreign key  |
| `@JoinTable`  | Defines join table   |
| `mappedBy`    | Defines inverse side |

Important concept:

```
Owning Side → Manages the relationship
Inverse Side → References the relationship
```

Understanding JPA relationships is essential for building **real-world Spring Boot applications with relational databases**.

---
