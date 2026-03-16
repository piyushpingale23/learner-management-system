# 24 — Spring Profiles

## 1. Introduction

Spring Profiles allow you to **define different configurations for different environments**, such as **development, testing, and production**.

* Helps manage environment-specific beans and properties
* Avoids hardcoding environment values
* Works seamlessly with Spring Boot’s `application.properties` or `application.yml`

Key concepts:

* `@Profile` annotation
* `application-{profile}.properties`
* Activating profiles

---

# 2. @Profile Annotation

`@Profile` is used to **activate a bean only for specific profiles**.

Example:

```java id="k0xh91"
@Configuration
@Profile("dev")
public class DevDatabaseConfig {

    @Bean
    public DataSource dataSource() {
        // Dev database configuration
        return new HikariDataSource();
    }
}
```

Explanation:

* Bean `dataSource()` is **only created** if the **dev profile is active**
* Profiles allow switching between multiple configurations

---

# 3. Profile-Specific Properties Files

Spring Boot supports **environment-specific properties files**:

```
application.properties          → default properties
application-dev.properties      → development environment
application-prod.properties     → production environment
```

### Example — application-dev.properties

```properties id="j5r2qf"
spring.datasource.url=jdbc:mysql://localhost:3306/devdb
spring.datasource.username=devuser
spring.datasource.password=devpass
logging.level.root=DEBUG
```

### Example — application-prod.properties

```properties id="x7v9um"
spring.datasource.url=jdbc:mysql://prod-server:3306/proddb
spring.datasource.username=produser
spring.datasource.password=prodpass
logging.level.root=ERROR
```

---

# 4. Activating Profiles

### 4.1 Using application.properties

```properties id="w3vh6z"
spring.profiles.active=dev
```

### 4.2 Using Command Line

```bash id="u1mf8s"
java -jar myapp.jar --spring.profiles.active=prod
```

### 4.3 Using Environment Variable

```bash id="x2qg7n"
export SPRING_PROFILES_ACTIVE=dev
```

* Spring Boot automatically loads:

    1. `application.properties` (default)
    2. `application-{profile}.properties` (profile-specific)
* Profile-specific properties **override default properties**

---

# 5. Combining @Profile with Beans

You can define **different beans for different profiles**:

```java id="c3e8mr"
@Configuration
public class AppConfig {

    @Bean
    @Profile("dev")
    public MessageService devMessageService() {
        return new DevMessageService();
    }

    @Bean
    @Profile("prod")
    public MessageService prodMessageService() {
        return new ProdMessageService();
    }
}
```

* Only the bean for the **active profile** is created
* Enables **environment-specific behavior**

---

# 6. Best Practices

* Use **profiles for environment separation** (dev, test, prod)
* Keep `application.properties` **for common/default properties**
* Avoid hardcoding URLs, credentials, or feature flags
* Use **@Profile** for beans that differ per environment
* Combine with **Spring Boot configuration properties** (`@ConfigurationProperties`) for better management

---

# 7. Summary

| Concept                       | Description                                   |
|-------------------------------|-----------------------------------------------|
| `@Profile`                    | Activate beans only for specified environment |
| `application-dev.properties`  | Properties file for dev environment           |
| `application-prod.properties` | Properties file for prod environment          |
| spring.profiles.active        | Property to set the active profile            |

Spring Profiles allow **flexible, environment-specific configuration**, ensuring the application behaves correctly in **development, testing, and production** environments.

---
