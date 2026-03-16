# 01 — Spring Boot Introduction

## 1. What is Spring Boot?

**Spring Boot** is a framework built on top of the **Spring Framework** that simplifies the development of Java-based applications, especially **REST APIs and Microservices**.

It eliminates the need for complex configuration and allows developers to build production-ready applications quickly.

Spring Boot provides:

* **Auto-configuration**
* **Embedded servers**
* **Starter dependencies**
* **Production-ready tools**

This allows developers to focus on **business logic instead of configuration**.

### Example

A simple Spring Boot application:

```java
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

The `@SpringBootApplication` annotation automatically enables:

* Component scanning
* Auto-configuration
* Configuration support

---

# 2. Why Spring Boot?

Before Spring Boot, building applications using the **Spring Framework** required a lot of manual setup.

Developers had to configure:

* XML configuration files
* DispatcherServlet
* View resolvers
* Database connections
* Dependency versions

Spring Boot was created to solve these problems and make development faster and easier.

### Main reasons to use Spring Boot

1. **Reduces configuration**
2. **Faster application development**
3. **Embedded servers (no external server required)**
4. **Production-ready features**
5. **Easy microservice development**

---

# 3. Problems with Traditional Spring Framework

Before Spring Boot, developers faced several challenges.

## 3.1 Too Much Configuration

Developers needed large XML configuration files.

Example:

```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
</bean>
```

Applications often had hundreds of configuration lines.

---

## 3.2 Dependency Management Problems

Managing compatible versions of libraries was difficult.

Example dependencies:

* Spring Core
* Spring MVC
* Jackson
* Hibernate

Developers had to ensure all versions were compatible.

---

## 3.3 Deployment Complexity

Applications had to be packaged as **WAR files** and deployed on external servers such as:

* Tomcat
* Jetty
* WebLogic

Deployment process:

```
Build WAR
Deploy to server
Restart server
Test application
```

This slowed down development.

---

## 3.4 Slow Development

Because of heavy configuration and deployment steps, development time increased significantly.

---

# 4. Advantages of Spring Boot

Spring Boot solves most of the problems of traditional Spring development.

## 4.1 Auto Configuration

Spring Boot automatically configures application components based on dependencies.

Example:

If you include:

```
spring-boot-starter-data-jpa
```

Spring Boot automatically configures:

* Hibernate
* DataSource
* Transaction management

---

## 4.2 Embedded Server

Spring Boot includes embedded servers such as:

* Tomcat
* Jetty
* Undertow

Applications can run directly using:

```
java -jar application.jar
```

No external server is required.

---

## 4.3 Starter Dependencies

Starter dependencies simplify dependency management.

Example:

```
spring-boot-starter-web
```

Automatically includes:

* Spring MVC
* Jackson
* Validation
* Embedded Tomcat

---

## 4.4 Production Ready

Spring Boot provides built-in production features such as:

* Health checks
* Metrics
* Monitoring
* External configuration

These features are available through **Spring Boot Actuator**.

---

## 4.5 Microservice Friendly

Spring Boot is widely used for building **microservices architecture** because it is:

* Lightweight
* Fast to start
* Easy to deploy

---

# 5. Key Features of Spring Boot

## 5.1 Auto Configuration

Spring Boot automatically configures beans based on dependencies.

Example:

If a database dependency exists, Spring Boot configures a **DataSource bean automatically**.

---

## 5.2 Starter Dependencies

Starter dependencies group common libraries together.

Examples:

```
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-validation
```

---

## 5.3 Embedded Server

Applications run without installing external servers.

Supported embedded servers:

* Tomcat (default)
* Jetty
* Undertow

---

## 5.4 Opinionated Configuration

Spring Boot follows **best practices by default**.

Developers can override configuration when necessary.

Example:

```
server.port=9090
```

---

## 5.5 Production Ready Features

Spring Boot provides production tools through **Actuator**.

Features include:

* Application health monitoring
* Metrics
* Environment information
* Application logs

Example endpoint:

```
/actuator/health
```

---

# 6. Spring Boot Application Flow

A typical Spring Boot application follows this flow:

```
Client Request
      ↓
DispatcherServlet
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
Database
```

This layered architecture helps maintain clean and scalable code.

---

# 7. Summary

Spring Boot simplifies the development of Java applications by reducing configuration and providing built-in features.

Key benefits:

* Rapid development
* Minimal configuration
* Embedded servers
* Easy dependency management
* Production-ready capabilities

Spring Boot is widely used for:

* REST APIs
* Microservices
* Cloud-based applications

---
