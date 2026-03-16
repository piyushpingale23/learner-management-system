# 22 — Hibernate Behind the Scenes

## 1. Introduction

Spring Data JPA uses **Hibernate** internally as the JPA implementation.

Understanding **what happens behind the scenes** is crucial for performance and correct behavior.

Key concepts:

* Persistence Context
* Dirty Checking
* First Level Cache
* Lazy vs Eager Loading

---

# 2. Persistence Context

**Persistence Context** is a **set of entity instances managed by Hibernate** in a session.

* Also called **EntityManager context**
* Tracks all entities loaded or saved during a transaction
* Ensures **entity identity**: same database row = same Java object

### Example

```
User u1 = entityManager.find(User.class, 1L);
User u2 = entityManager.find(User.class, 1L);

System.out.println(u1 == u2); // true
```

Even though `find()` is called twice, Hibernate returns **the same object instance**.

### Benefits

* Avoids duplicate objects
* Improves performance
* Manages lifecycle automatically

---

# 3. Dirty Checking

**Dirty checking** is Hibernate’s mechanism to **detect changes in entities**.

* Hibernate automatically tracks entity changes inside the persistence context
* No explicit `UPDATE` queries are needed

### Example

```java
@Transactional
public void updateUser(Long id) {
    User user = userRepository.findById(id).get();
    user.setName("Updated Name");
    // No need to call save()
}
```

**What happens internally:**

1. Hibernate loads the entity into persistence context
2. Changes are detected during flush/commit
3. Hibernate generates `UPDATE` SQL automatically

---

# 4. First Level Cache

**First Level Cache**:

* Exists in **Session / EntityManager**
* Enabled by default
* Stores entities **inside the persistence context**
* Only works **within the same transaction/session**

### Example

```java
@Transactional
public void getUserTwice(Long id) {
    User u1 = entityManager.find(User.class, id);
    User u2 = entityManager.find(User.class, id);

    // Only one SQL SELECT is executed
}
```

Key points:

* Reduces database hits
* Ensures **identity and consistency** within transaction
* Cleared automatically when transaction ends

---

# 5. Lazy vs Eager Loading

Fetching strategy determines **when related entities are loaded**.

### 5.1 Eager Loading (`FetchType.EAGER`)

* Loads the related entity **immediately**
* Can cause performance issues if many relations exist

Example:

```java
@OneToMany(fetch = FetchType.EAGER)
private List<Order> orders;
```

* When a `Customer` is loaded, all its `Orders` are loaded immediately

### 5.2 Lazy Loading (`FetchType.LAZY`)

* Loads related entities **on demand**
* Reduces initial database hits
* Requires **active session** to access data

Example:

```java
@OneToMany(fetch = FetchType.LAZY)
private List<Order> orders;
```

* Orders are only fetched **when getter is called**
* Commonly used to optimize performance

### Key Difference

| Fetch Type | When data is loaded | Pros        | Cons                                          |
|------------|---------------------|-------------|-----------------------------------------------|
| LAZY       | On access           | Performance | LazyInitializationException if session closed |
| EAGER      | Immediately         | Simple      | May load unnecessary data                     |

---

# 6. Summary

Hibernate manages **entities and database operations behind the scenes**.

| Concept             | Description                                         |
|---------------------|-----------------------------------------------------|
| Persistence Context | Tracks entities in a transaction, ensures identity  |
| Dirty Checking      | Automatically detects entity changes and updates DB |
| First Level Cache   | Stores entities in session, reduces DB hits         |
| Lazy Loading        | Fetch related entities on demand                    |
| Eager Loading       | Fetch related entities immediately                  |

Understanding these concepts is essential for:

* Writing efficient queries
* Avoiding unnecessary database calls
* Correctly managing entity relationships and transactions

---
