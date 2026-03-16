# 21 — Transaction Management

## 1. Introduction

In Spring Boot, **transaction management** ensures that a sequence of database operations are executed as a single **unit of work**.

If one operation fails, the entire transaction can be **rolled back**, maintaining data integrity.

Spring Boot provides **declarative transaction management** using the `@Transactional` annotation.

---

# 2. @Transactional Annotation

`@Transactional` is used to **define the boundary of a transaction**.

Example:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUserAndProfile(User user, Profile profile) {
        userRepository.save(user);
        profileRepository.save(profile);
        // If any exception occurs, both operations are rolled back
    }
}
```

### Key Points

* Applied at **method** or **class level**
* Spring creates a **proxy** to manage transaction
* Automatically **commits** or **rolls back** based on exceptions

---

# 3. ACID Properties

Transactions must follow **ACID properties**:

| Property    | Description                                        |
|-------------|----------------------------------------------------|
| Atomicity   | All operations in a transaction succeed or none do |
| Consistency | Database moves from one valid state to another     |
| Isolation   | Concurrent transactions do not interfere           |
| Durability  | Once committed, changes are permanent              |

Example:

```text
Transfer $100 from Account A to B
- Debit A
- Credit B
If Credit fails → Debit is rolled back
```

---

# 4. Transaction Propagation

**Propagation** defines how transactions behave when a **transactional method calls another transactional method**.

Common propagation types:

| Type          | Description                                                          |
|---------------|----------------------------------------------------------------------|
| REQUIRED      | Use existing transaction or create new if none exists (default)      |
| REQUIRES_NEW  | Always create a new transaction, suspending existing one             |
| SUPPORTS      | Join existing transaction if present, else execute non-transactional |
| NOT_SUPPORTED | Execute non-transactional, suspending existing transaction           |
| MANDATORY     | Must run within a transaction; throws exception if none exists       |
| NEVER         | Must not run within a transaction; throws exception if one exists    |
| NESTED        | Run within a nested transaction (savepoint)                          |
 
Example:

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void auditLog() { }
```

---

# 5. Rollback

By default, **runtime exceptions** cause rollback, **checked exceptions do not**.

### Example

```java
@Transactional(rollbackFor = Exception.class)
public void process() {
    // All operations are rolled back on any exception
}
```

* `rollbackFor` → specify exceptions to trigger rollback
* `noRollbackFor` → specify exceptions to **ignore** for rollback

---

# 6. Isolation Levels

Isolation defines **how transactions interact with each other**.

| Level            | Description                            |
|------------------|----------------------------------------|
| READ_UNCOMMITTED | Dirty reads allowed                    |
| READ_COMMITTED   | Prevents dirty reads                   |
| REPEATABLE_READ  | Prevents non-repeatable reads          |
| SERIALIZABLE     | Full isolation, prevents phantom reads |

Example:

```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
public void updateInventory() { }
```

---

# 7. Read-Only Transactions

For queries that **do not modify data**, use **read-only transactions** for optimization.

```java
@Transactional(readOnly = true)
public List<User> getAllUsers() { }
```

Benefits:

* Reduces database locking
* Improves performance

---

# 8. Advanced Topics

### 1. Nested Transactions

* Use `Propagation.NESTED` for **savepoint**
* Allows partial rollback without affecting outer transaction

### 2. Transaction Timeout

```java
@Transactional(timeout = 5) // seconds
public void process() { }
```

* Rolls back if transaction exceeds specified time

### 3. Programmatic Transactions

* Instead of `@Transactional`, you can manage transactions manually using:

```
PlatformTransactionManager txManager;
TransactionStatus status = txManager.getTransaction(new DefaultTransactionDefinition());

try {
    // business logic
    txManager.commit(status);
} catch (Exception e) {
    txManager.rollback(status);
}
```

---

# 9. Best Practices

* Use **declarative transactions (`@Transactional`)** instead of programmatic
* Place `@Transactional` on **service layer**, not repository or controller
* Avoid calling transactional methods from the **same class** (self-invocation) because proxies won’t work
* Set appropriate **propagation and isolation levels**
* Use **rollbackFor** for checked exceptions if necessary
* Mark read-only queries with `readOnly = true`

---

# 10. Summary

Transaction management ensures **data consistency and integrity** in Spring Boot applications.

Key concepts:

| Concept          | Description                                   |
|------------------|-----------------------------------------------|
| `@Transactional` | Declares transaction boundary                 |
| ACID             | Atomicity, Consistency, Isolation, Durability |
| Propagation      | Controls transaction behavior in nested calls |
| Rollback         | Automatically undo operations on exception    |
| Isolation        | Controls visibility of uncommitted changes    |
| Read-only        | Optimizes read operations                     |

Using proper transaction management helps **avoid data corruption, deadlocks, and inconsistent states**.

---
