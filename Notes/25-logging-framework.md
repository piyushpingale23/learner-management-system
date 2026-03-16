# 25 — Logging in Spring Boot

## 1. Introduction

Logging is essential for **monitoring, debugging, and auditing** applications.
Spring Boot provides support for multiple logging frameworks out-of-the-box, with **SLF4J and Logback** being the most common.

---

# 2. SLF4J (Simple Logging Facade for Java)

**SLF4J** is a **logging abstraction layer**.

* Provides a **common API** for different logging frameworks (Logback, Log4j, JUL)
* Lets you **switch frameworks without changing code**
* Acts as a facade, not an implementation

### Example Usage

```java id="v29x8w"
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public void createUser(User user) {
        LOGGER.info("Creating user: {}", user.getName());
        LOGGER.debug("User details: {}", user);
        LOGGER.error("Error occurred", new RuntimeException("Sample error"));
    }
}
```

Key Points:

* `LoggerFactory.getLogger(Class)` → Get logger for the class
* Use placeholders `{}` instead of string concatenation
* Supports levels: `TRACE`, `DEBUG`, `INFO`, `WARN`, `ERROR`

---

# 3. Logback

**Logback** is the **default logging implementation** in Spring Boot.

* Fast, reliable, and highly configurable
* Fully compatible with SLF4J
* Configuration via `logback.xml` or `application.properties`

### 3.1 Using application.properties

```properties id="kq0s2r"
logging.level.root=INFO
logging.level.com.example=DEBUG
logging.file.name=app.log
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
```

Explanation:

| Property                   | Description                          |
|----------------------------|--------------------------------------|
| logging.level.root         | Set default log level                |
| logging.level.package.name | Set log level for a specific package |
| logging.file.name          | File to write logs                   |
| logging.pattern.console    | Format of console logs               |

### 3.2 Using logback.xml

```xml id="h7p4nc"
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>
```

* More flexible than `application.properties`
* Supports **multiple appenders, filters, and rolling policies**

---

# 4. Log Levels

Common log levels (from low to high):

| Level | Description                          |
|-------|--------------------------------------|
| TRACE | Very detailed, used for debugging    |
| DEBUG | Debug information, development focus |
| INFO  | General runtime information          |
| WARN  | Potentially harmful situations       |
| ERROR | Serious failures, exceptions         |

---

# 5. Best Practices

* Use **SLF4J API** for logging (decouples code from implementation)
* Set **different log levels** for different packages
* Avoid **System.out.println** for production
* Configure **rolling files** for long-running apps
* Use **placeholders `{}`** instead of string concatenation

---

# 6. Summary

| Concept        | Purpose                                                         |
|----------------|-----------------------------------------------------------------|
| SLF4J          | Logging abstraction layer, decouples code from implementation   |
| Logback        | Default logging framework in Spring Boot, compatible with SLF4J |
| Logging levels | TRACE, DEBUG, INFO, WARN, ERROR                                 |
| Configuration  | `application.properties` or `logback.xml`                       |

Logging ensures **visibility into the application behavior**, helps debug issues, and improves **maintainability and monitoring** in Spring Boot applications.

---
