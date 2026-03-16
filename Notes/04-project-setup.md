# 04 — Spring Boot Project Setup

## 1. Introduction

Setting up a Spring Boot project is very simple because Spring Boot provides tools that automatically generate a project with the correct configuration.

The most common ways to create a Spring Boot project are:

* Using **Spring Initializer**
* Using **Maven**
* Using **Gradle**
* Using **IDE tools (IntelliJ / Eclipse / VS Code)**

Spring Boot projects follow a **standard folder structure** that helps maintain clean and scalable applications.

---

# 2. Spring Initializer

Spring Initializer is the **official tool for generating Spring Boot projects**.

It automatically creates a project with:

* Proper folder structure
* Required dependencies
* Build configuration
* Main application class

### Website

```text
https://start.spring.io
```

---

## Steps to Create Project using Spring Initializer

1. Open **Spring Initializer**
2. Select **Project Type**
3. Select **Language**
4. Select **Spring Boot Version**
5. Enter project metadata
6. Add dependencies
7. Generate the project

---

### Example Configuration

| Field        | Example          |
|--------------|------------------|
| Project      | Maven            |
| Language     | Java             |
| Spring Boot  | 3.x              |
| Group        | com.example      |
| Artifact     | demo             |
| Name         | demo             |
| Package Name | com.example.demo |
| Packaging    | Jar              |
| Java Version | 17               |

---

### Example Dependencies

Common dependencies:

* Spring Web
* Spring Data JPA
* Spring Security
* Validation
* Lombok
* H2 Database

After generating the project, download and extract the ZIP file.

---

# 3. Maven Setup

**Maven** is a build and dependency management tool used in many Java projects.

Spring Boot projects created using Maven contain a file called:

```text
pom.xml
```

---

## Example pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
    </parent>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>

</project>
```

---

## Important Maven Sections

### Parent

```xml
<parent>
 spring-boot-starter-parent
</parent>
```

Manages dependency versions.

---

### Dependencies

Defines project dependencies.

Example:

```
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-validation
```

---

### Build

Defines how the application is built.

Example plugin:

```
spring-boot-maven-plugin
```

This plugin allows running the application using:

```bash
mvn spring-boot:run
```

---

# 4. Gradle Setup

Gradle is another build automation tool used in modern Java projects.

Gradle projects contain a file:

```text
build.gradle
```

---

## Example build.gradle

```gradle
plugins {
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management'
    id 'java'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

---

## Gradle Commands

Run application:

```bash
./gradlew bootRun
```

Build project:

```bash
./gradlew build
```

---

# 5. Spring Boot Folder Structure

A Spring Boot project follows a **standard directory structure**.

```text
project-name
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/example/demo
│   │   │       ├── controller
│   │   │       ├── service
│   │   │       ├── repository
│   │   │       ├── model
│   │   │       └── DemoApplication.java
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   │
│   └── test
│       └── java
│
├── pom.xml
└── README.md
```

---

# 6. Folder Explanation

## src/main/java

Contains the **application source code**.

Typical packages:

* controller
* service
* repository
* model
* configuration

---

## src/main/resources

Contains application resources.

Important files:

```text
application.properties
application.yml
```

Other folders:

### static

Contains static resources:

* HTML
* CSS
* JavaScript
* Images

---

### templates

Used for server-side template engines such as:

* Thymeleaf
* FreeMarker

---

## src/test/java

Contains **unit tests and integration tests**.

Example test frameworks:

* JUnit
* Mockito
* Spring Boot Test

---

# 7. Main Application Class

Every Spring Boot application contains a **main class**.

Example:

```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

`@SpringBootApplication` enables:

* Component scanning
* Auto configuration
* Spring Boot configuration

---

# 8. Running a Spring Boot Application

Applications can be run using several methods.

### Run using IDE

Run the main class.

---

### Run using Maven

```bash
mvn spring-boot:run
```

---

### Run using JAR

```bash
java -jar application.jar
```

---

# 9. Best Practices for Project Structure

Recommended package structure:

```text
com.company.project
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── config
```

This structure keeps the application **organized and maintainable**.

---

# 10. Summary

Spring Boot provides easy ways to create and manage projects using tools such as **Spring Initializer**, **Maven**, and **Gradle**.

The framework also provides a **standard project structure**, which helps developers maintain clean and scalable applications.

Key points:

* Spring Initializer simplifies project creation
* Maven and Gradle manage dependencies
* Spring Boot follows a standard folder structure
* Applications run using an embedded server

---
