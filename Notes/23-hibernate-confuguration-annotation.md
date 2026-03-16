# 23 — Configuration Annotations

## 1. Introduction

Spring Boot provides **configuration annotations** to define **beans, properties, and application settings** in a clean, declarative way.

Key annotations:

* `@Configuration`
* `@Bean`
* `@Value`

These annotations are widely used for:

* Defining custom beans
* Injecting values from **application.properties** or **application.yml**
* Managing configuration logic centrally

---

# 2. @Configuration

`@Configuration` is used to **mark a class as a source of bean definitions**.

* Spring will scan this class and manage all `@Bean` methods
* Replaces XML configuration

### Example

```java id="b9v89d"
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
```

Explanation:

* `AppConfig` is now a **configuration class**
* Spring Boot will manage `userService()` as a **bean**
* You can inject it elsewhere using `@Autowired`

---

# 3. @Bean

`@Bean` is used to **define a single bean inside a `@Configuration` class**.

* Each `@Bean` method returns an instance managed by Spring
* Default scope is **singleton**

### Example

```java id="v5y71c"
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Usage:

```java id="g7xk92"
@Autowired
private PasswordEncoder passwordEncoder;
```

Spring will automatically inject the bean.

### Bean Scopes

By default:

* **Singleton** → one instance per application context

Other scopes:

| Scope       | Description                     |
|-------------|---------------------------------|
| prototype   | New instance every time         |
| request     | One instance per HTTP request   |
| session     | One instance per HTTP session   |
| application | One instance per ServletContext |

---

# 4. @Value

`@Value` is used to **inject values from configuration files** like `application.properties` or `application.yml`.

* Can inject strings, numbers, lists, expressions
* Supports SpEL (Spring Expression Language)

### Example 1 — Simple Value

**application.properties**

```properties
app.name=MySpringApp
```

**Java Class**

```java id="u3fh8v"
@Value("${app.name}")
private String appName;
```

### Example 2 — Default Value

```java id="k2q7xo"
@Value("${app.description:Default Description}")
private String appDescription;
```

* If property is missing, default value is used

### Example 3 — Expression

```java id="c5z1lp"
@Value("#{2 * 5}")
private int result;
```

* Injects `10` using SpEL

---

# 5. Combining @Configuration, @Bean, @Value

Example:

```java id="h7x8fq"
@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Bean
    public UserService userService() {
        return new UserService(appName);
    }
}
```

Usage:

``` id="d2z9c0"
@Autowired
private UserService userService;

System.out.println(userService.getAppName());
```

Spring automatically injects:

* `appName` from properties
* `UserService` as a managed bean

---

# 6. Best Practices

* Use `@Configuration` for **centralized configuration**
* Define beans with `@Bean` only when needed
* Use `@Value` for **externalized configuration** rather than hardcoding values
* Prefer **constructor injection** over field injection when possible
* Keep configuration classes **lightweight** and focused

---

# 7. Summary

| Annotation       | Purpose                                                     |
|------------------|-------------------------------------------------------------|
| `@Configuration` | Marks a class as a configuration source for Spring beans    |
| `@Bean`          | Defines a single Spring-managed bean                        |
| `@Value`         | Injects values from properties, environment, or expressions |

These annotations make Spring Boot **flexible, modular, and easy to configure**, avoiding XML configuration entirely.

---
