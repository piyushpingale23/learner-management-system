# 06 — application.properties

## 1. Introduction

`application.properties` is the **main configuration file in Spring Boot**.

It is used to configure application settings such as:

* Server configuration
* Database connection
* Logging configuration
* JPA/Hibernate settings
* Custom application properties

Spring Boot automatically reads this file during application startup.

Location:

```
src/main/resources/application.properties
```

Spring Boot also supports another configuration format:

```
application.yml
```

Both files serve the same purpose but use different syntax.

---

# 2. Server Configuration

Spring Boot applications run on an **embedded server (Tomcat by default)**.

You can configure the server using properties.

## Change Server Port

Default port:

```
8080
```

Example:

```properties
server.port=9090
```

Application URL becomes:

```
http://localhost:9090
```

---

## Configure Application Context Path

Example:

```properties
server.servlet.context-path=/api
```

Application endpoint example:

```
http://localhost:9090/api/users
```

---

# 3. Datasource Configuration

Spring Boot uses **Datasource configuration** to connect to a database.

Common properties:

| Property                            | Purpose                 |
|-------------------------------------|-------------------------|
| spring.datasource.url               | Database connection URL |
| spring.datasource.username          | Database username       |
| spring.datasource.password          | Database password       |
| spring.datasource.driver-class-name | JDBC driver             |

---

## Example (MySQL)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

---

## Example (H2 Database)

```properties
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
```

---

# 4. JPA Configuration

Spring Boot provides configuration properties for **JPA and Hibernate**.

| Property                                   | Purpose                    |
|--------------------------------------------|----------------------------|
| spring.jpa.hibernate.ddl-auto              | Schema generation strategy |
| spring.jpa.show-sql                        | Show SQL queries           |
| spring.jpa.properties.hibernate.format_sql | Format SQL output          |

---

## Example

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Hibernate ddl-auto Options

| Value       | Description                 |
|-------------|-----------------------------|
| none        | No schema generation        |
| validate    | Validate schema             |
| update      | Update schema automatically |
| create      | Create schema every startup |
| create-drop | Create and drop schema      |

Example:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

# 5. Logging Configuration

Spring Boot uses **SLF4J + Logback** for logging.

You can control log levels using `logging.level`.

---

## Example

```properties
logging.level.root=INFO
```

---

## Package Level Logging

```properties
logging.level.org.springframework=INFO
logging.level.com.example=DEBUG
```

Meaning:

* `INFO` → Standard information
* `DEBUG` → Detailed debugging logs

---

## Logging Levels

| Level | Description           |
|-------|-----------------------|
| TRACE | Very detailed logs    |
| DEBUG | Debugging information |
| INFO  | General information   |
| WARN  | Warning messages      |
| ERROR | Error messages        |

---

# 6. Custom Application Properties

Developers can define custom properties.

Example:

```properties
app.name=UserManagementSystem
app.version=1.0
```

These properties can be accessed in code using `@Value`.

Example:

```java
@Value("${app.name}")
private String appName;
```

---

# 7. application.yml

Spring Boot also supports **YAML configuration format**.

File location:

```
src/main/resources/application.yml
```

YAML uses **hierarchical structure instead of key-value pairs**.

---

## Example application.yml

```yaml
server:
  port: 9090

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## Difference Between properties and YAML

| Feature            | application.properties            | application.yml        |
|--------------------|-----------------------------------|------------------------|
| Format             | Key-value pairs                   | Hierarchical structure |
| Readability        | Less readable for complex configs | More readable          |
| Multi-level config | Harder                            | Easier                 |
| File extension     | .properties                       | .yml                   |

Example comparison:

### Properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
```

### YAML

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test
    username: root
```

---

# 8. Configuration Priority

Spring Boot reads configuration properties in a specific order.

Priority (highest first):

```
Command Line Arguments
Environment Variables
application.yml
application.properties
Default Configuration
```

Higher priority values override lower ones.

---

# 9. Best Practices

Recommended practices when using configuration files:

* Keep sensitive values outside the codebase
* Use environment variables for production
* Use YAML for complex configurations
* Organize configuration logically

Example grouping:

```yaml
app:
  name: user-service
  version: 1.0
```

---

# 10. Summary

`application.properties` and `application.yml` are used to configure Spring Boot applications.

Common configurations include:

* Server configuration
* Database connection
* JPA/Hibernate settings
* Logging configuration

Key points:

* Located in `src/main/resources`
* Automatically loaded by Spring Boot
* Supports both `.properties` and `.yml` formats
* Used to configure most application settings

These configuration files allow developers to **externalize application configuration and manage environments easily**.

---
