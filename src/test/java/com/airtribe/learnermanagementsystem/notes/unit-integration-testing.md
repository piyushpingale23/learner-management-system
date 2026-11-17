# Unit Testing

Unit testing means testing a **single unit (method/class)** in isolation to verify it works correctly. <br>
In isolation means, we test a method/class without depending on real external components.

## Key Points

* Focus: Smallest piece of code
* No dependency on database, APIs, or other classes
* Usually uses mocking

---

## Example

```java
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
}
```

---

## Unit Test (Using JUnit)

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calculator = new Calculator();

        int result = calculator.add(2, 3);

        assertEquals(5, result);
    }
}
```

---

# Integration Testing

Integration testing means testing **multiple components together** to verify they interact correctly.

## Key Points

* Focus: Interaction between classes/modules
* Includes DB, APIs, services, etc.
* No mocking (or minimal mocking)

---

## Example Scenario

You have:
* `UserService`
* `UserRepository`

---

## Actual Code

```java
// Repository
public class UserRepository {

    public String getUserNameById(int id) {
        // Simulating DB call
        if (id == 1) return "Jack";
        return null;
    }
}
```

```java
// Service
public class UserService {

    private UserRepository userRepository = new UserRepository();

    public String getUserName(int id) {
        return userRepository.getUserNameById(id);
    }
}
```

---

## Integration Test

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceIntegrationTest {

    @Test
    void testGetUserName() {
        UserService userService = new UserService();

        String result = userService.getUserName(1);

        assertEquals("Jack", result);
    }
}
```

---

# Unit vs Integration Testing (Comparison)

| Feature      | Unit Testing        | Integration Testing    |
|--------------|---------------------|------------------------|
| Scope        | Single method/class | Multiple components    |
| Speed        | Very fast           | Slower                 |
| Dependencies | Mocked              | Real                   |
| Purpose      | Logic correctness   | Flow correctness       |
| Example      | `Calculator.add()`  | `Service → Repository` |

---
