# 02 — Spring vs Spring Boot

## 1. Introduction

**Spring Framework** is a powerful and flexible framework used for building enterprise Java applications. However, it requires a lot of **manual configuration and setup**.

**Spring Boot** is built on top of the Spring Framework to simplify application development by providing **autoconfiguration, embedded servers, and starter dependencies**.

Spring Boot reduces the complexity of using Spring and helps developers build applications **faster with minimal configuration**.

---

# 2. Configuration

## Spring Framework

In traditional Spring applications, configuration was done manually using:

* XML configuration files
* Java configuration classes
* Web configuration files

Example XML configuration:

```xml
<bean id="userService" class="com.example.service.UserService">
</bean>
```

Example Java configuration:

```java
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
```

Developers needed to configure:

* Beans
* DispatcherServlet
* View resolvers
* Database connections

This often resulted in **large configuration files**.

---

## Spring Boot

Spring Boot eliminates most manual configuration using **auto-configuration**.

Example:

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

`@SpringBootApplication` automatically enables:

* `@Configuration`
* `@EnableAutoConfiguration`
* `@ComponentScan`

This significantly reduces the amount of configuration needed.

---

# 3. Dependency Management

## Spring Framework

Developers had to manually add and manage dependencies.

Example Maven dependencies:

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
</dependency>

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
</dependency>
```

Problems:

* Developers had to ensure compatible versions
* Dependency conflicts were common
* Setup time increased

---

## Spring Boot

Spring Boot provides **Starter Dependencies**, which group commonly used libraries together.

Example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This starter automatically includes:

* Spring MVC
* Jackson (JSON processing)
* Validation
* Embedded Tomcat

Developers do not need to manage individual dependency versions.

---

# 4. Server Setup

## Spring Framework

Applications were packaged as **WAR files** and deployed on external servers.

Common servers:

* Apache Tomcat
* Jetty
* WebLogic

Deployment process:

```
1. Build WAR file
2. Deploy to server
3. Start or restart server
4. Access application
```

This process slowed down development.

---

## Spring Boot

Spring Boot provides **embedded servers**.

Supported servers:

* Tomcat (default)
* Jetty
* Undertow

Applications can run directly using:

```
java -jar application.jar
```

No external server installation is required.

---

# 5. Development Speed

## Spring Framework

Development was slower because developers had to:

* Write configuration files
* Manage dependencies
* Configure servers
* Deploy applications manually

This increased the **time required to build and test applications**.

---

## Spring Boot

Spring Boot significantly increases development speed because:

* Auto configuration reduces setup time
* Starter dependencies simplify dependency management
* Embedded servers remove deployment complexity
* Production tools are built-in

Developers can focus on **business logic instead of configuration**.

---

# 6. Comparison Table

| Feature               | Spring Framework                         | Spring Boot                                         |
|-----------------------|------------------------------------------|-----------------------------------------------------|
| Configuration         | Requires manual configuration (XML/Java) | Minimal configuration with auto-configuration       |
| Dependency Management | Manual dependency setup                  | Starter dependencies simplify dependency management |
| Server Setup          | Requires external server                 | Embedded server included                            |
| Deployment            | WAR file deployment                      | Run using `java -jar`                               |
| Development Speed     | Slower due to configuration              | Faster development                                  |
| Microservices Support | More setup required                      | Designed for microservices                          |
| Production Features   | Requires additional setup                | Built-in production features (Actuator)             |

---

# 7. When to Use Spring Framework

Spring Framework may be used when:

* Full control over configuration is required
* Building highly customized enterprise systems
* Legacy applications already use Spring

---

# 8. When to Use Spring Boot

Spring Boot is preferred when:

* Building REST APIs
* Developing microservices
* Rapid application development is required
* Cloud-native applications are being developed

Most modern Java applications use **Spring Boot instead of traditional Spring**.

---

# 9. Summary

Spring Framework provides the foundation for building Java applications but requires significant configuration.

Spring Boot simplifies this process by providing:

* Auto-configuration
* Embedded servers
* Starter dependencies
* Production-ready features

As a result, Spring Boot enables **faster development, easier deployment, and simplified configuration**.

---
