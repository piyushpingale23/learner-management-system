# Types of Software Testing

---

#  1. Based on Level (MOST IMPORTANT)

## 1. Unit Testing

Tests a **single method or class in isolation**.

### Key Points

* Done by developers
* Fast and lightweight
* Uses mocks for dependencies

### Tools

* JUnit
* Mockito

### Example

```
assertEquals(5, calculator.add(2,3));
```

---

## 2. Integration Testing

Tests **interaction between multiple components**.

### Key Points

* Verifies modules work together
* Includes DB/API interaction
* No or minimal mocking

### Tools

* SpringBootTest
* TestContainers
* RestTemplate / WebTestClient

### Example

```
UserService → UserRepository → Database
```

---

## 3. System Testing

Tests the **complete application as a whole**.

### Key Points

* Full system validation
* Includes all layers (UI + Backend + DB)
* Done in staging environment

### Tools

* Selenium
* Cypress

### Example

```
Login → Add to Cart → Payment → Order Success
```

---

## 4. Acceptance Testing (UAT)

Tests whether the system meets **business requirements**.

### Key Points

* Done by clients or business users
* Final validation before release

### Tools

* Manual testing
* Cucumber (BDD)

### Example

```
User can successfully place an order
```

---

# 2. Based on Purpose

## 5. Regression Testing

Ensures **existing features still work after changes**.

### Key Points

* Run after bug fixes or new features
* Prevents breaking old functionality

### Tools

* Selenium
* JUnit (automation suites)

### Example

```
After adding payment feature → verify login still works
```

---

##  6. Smoke Testing

Basic tests to check if **build is stable**.

### Key Points

* Quick and shallow testing
* Decides if build is testable

### Tools

* Jenkins (CI pipelines)
* Postman (basic API checks)

### Example

```
App starts?
Login works?
```

If smoke fails → Reject build

---

## 7. Sanity Testing

Quick test of **specific functionality after small change**.

### Key Points

* Focused and narrow
* Done after bug fix

### Tools

* Manual / JUnit

### Example

```
Test only payment flow after fixing payment bug
```

---

# 3. Based on Code Visibility

## 8. Black Box Testing

Tests **without knowing internal code**.

### Key Points

* Focus on input/output
* Used in UI & API testing

### Tools

* Selenium
* Postman

### Example

```
Enter username → expect success message
```

---

## 9. White Box Testing

Tests **with full knowledge of code**.

### Key Points

* Covers logic, branches, loops
* Mostly unit testing

### Tools

* JUnit
* Mockito

### Example

```
Testing if conditions and loops work correctly
```

---

## 10. Grey Box Testing

Tests with **partial knowledge of system**.

### Key Points

* Combination of black + white box
* Common in integration testing

---

# 4. Special Types

## 11. Performance Testing

Checks **speed, scalability, and stability**.

### Types

* Load Testing
* Stress Testing

### Tools

* JMeter
* Gatling

### Example

```
1000 users hitting API simultaneously
```

---

## 12. Security Testing

Checks for **vulnerabilities and threats**.

### Key Points

* Prevents attacks
* Ensures data safety

### Tools

* OWASP ZAP
* Burp Suite

### Example

```
SQL Injection, Authentication bypass
```

---

## 13. API Testing

Tests **backend APIs directly**.

### Key Points

* No UI involved
* Validates request/response

### Tools

* Postman
* RestAssured

### Example

```
POST /users  
GET /orders
```

---

## 14. UI Testing

Tests **frontend behavior and design**.

### Key Points

* Validates UI elements
* Checks user interactions

### Tools

* Selenium
* Cypress

---

## 15. End-to-End (E2E) Testing

Tests **complete user journey across system**.

### Key Points

* Covers UI + Backend + DB
* Slow but very important

### Tools

* Selenium
* Cypress
* Playwright

### Example

```
Signup → Login → Purchase → Logout
```

---

# Final Summary

* Unit → Smallest part
* Integration → Components together
* System → Full application
* E2E → Real user journey
* Regression → Old features check
* Smoke → Build health check
* Sanity → Specific fix check

---
