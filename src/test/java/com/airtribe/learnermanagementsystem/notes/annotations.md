# Testing Annotations (JUnit + Mockito + Spring Boot)

## 1. JUnit (Testing Framework)

JUnit is a framework used to write and run test cases in Java.

### Purpose

Define test methods , Execute tests , Generate test results

## 2. Mockito (Mocking Framework)

Mockito is used to create mock (fake) objects for testing.

## 3. Assertion Framework

Assertions are used to verify expected vs actual results.

| Assertion                | Meaning            |
|--------------------------|--------------------|
| `assertEquals(a, b)`     | check equality     |
| `assertTrue(condition)`  | must be true       |
| `assertFalse(condition)` | must be false      |
| `assertNull(obj)`        | should be null     |
| `assertNotNull(obj)`     | should not be null |
| `assertThrows()`         | expect exception   |

---

# 1. JUnit 5 Annotations

## `@Test`

Marks a method as a test case.

```java
@Test
void testAddition() {
    assertEquals(5, 2 + 3);
}
```

---

## `@DisplayName`

Gives a custom name to the test.

```java
@Test
@DisplayName("Test addition of two numbers")
void testAdd() {
    assertEquals(5, 2 + 3);
}
```

---

# 2. Mockito Annotations

## `@Mock`

`@Mock` is used to create a **fake (dummy) object** of a class.

```java
@Mock
PaymentService paymentService;
```

---

## What happens internally?

When you use `@Mock`, Mockito:

* Creates a **proxy object**
* Overrides all methods

### Default return values:

* `null` → for objects
* `0` → for numbers
* `false` → for boolean

Unless you define behavior:

```
when(paymentService.processPayment()).thenReturn(true);
```

---

## When to use `@Mock`?

Use `@Mock` when:

* Class depends on:

    * Database
    * External API
    * Another service
* You want **unit testing (in isolation)**

---

## Important

* It **does NOT create a real object**
* It creates a **mock version**

---

# `@InjectMocks`

Injects mock dependencies into the class being tested.

```java
@InjectMocks
OrderService orderService;
```

---

## Key Understanding

* NOT a mock
* Creates a **real object**
* Injects `@Mock` dependencies into it

---

## Comparison

| Annotation     | Object Type                     |
|----------------|---------------------------------|
| `@Mock`        | Fake (dummy) object             |
| `@InjectMocks` | Real object (with mocks inside) |

---

## Example

### Classes

```java
public class PaymentService {
    public boolean processPayment() {
        return true; // real logic
    }
}
```

```java
public class OrderService {

    private PaymentService paymentService;

    // Constructor injection
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean placeOrder() {
        return paymentService.processPayment();
    }
}
```

---

### Test

```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    PaymentService paymentService;   // fake

    @InjectMocks
    OrderService orderService;       // real

    @Test
    void testOrder() {
        when(paymentService.processPayment()).thenReturn(true);

        boolean result = orderService.placeOrder();

        assertTrue(result);
    }
}
```

---

# 3. Spring Boot Testing Annotations

---

## `@SpringBootTest`

Loads the **full application context**.

```java
@SpringBootTest
class ApplicationTest {

    @Test
    void contextLoads() {
    }
}
```

---

## `@WebMvcTest`

Tests only **Spring MVC (controllers)**.

```java
@WebMvcTest(UserController.class)
class UserControllerTest {
}
```

---

## `@DataJpaTest`

Tests only **JPA repositories**.

```java
@DataJpaTest
class UserRepositoryTest {
}
```

---

## `@MockBean`

Creates a mock and adds it to the **Spring context**.

```java
@MockBean
UserService userService;
```

---

## Example (Spring Boot Test)

```java
@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void testUser() {
        when(userRepository.findById(1L))
            .thenReturn(Optional.of(new User()));

        assertNotNull(userService.getUser(1L));
    }
}
```

---

# Quick Summary

* `@Test` → Marks test method
* `@DisplayName` → Custom test name
* `@Mock` → Fake dependency
* `@InjectMocks` → Real object with mocks injected
* `@SpringBootTest` → Full app testing
* `@WebMvcTest` → Controller testing
* `@DataJpaTest` → Repository testing
* `@MockBean` → Mock inside Spring context

---
