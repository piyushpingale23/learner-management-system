# Learner Management System

This project is a **Spring Boot Learner Management System** built for learning purposes. It uses **H2 in-memory database** and **Spring Data JPA**.

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database
* Maven

---

##  How to Run the Application

### Run using Maven Wrapper

```bash
./mvnw spring-boot:run
```

OR run directly from your IDE by executing:

```
LearnerManagementSystemApplication.java
```

---

## Application URL

Once the application starts successfully, it will run on:

```
http://localhost:8080
```

---

## H2 Database Console

H2 is an **in-memory database**, mainly used for development and learning.

### H2 Console URL

Open your browser and go to:

```
http://localhost:8080/h2-console
```

---

### H2 Console Login Details

Use the following details on the H2 login page:

| Field        | Value               |
| ------------ | ------------------- |
| **JDBC URL** | `jdbc:h2:mem:lmsdb` |
| **Username** | `sa`                |
| **Password** | *(leave empty)*     |

Make sure the **JDBC URL matches** the one defined in `application.properties`.

---

##  H2 Configuration (application.properties)

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:mem:lmsdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Important Notes

* H2 **in-memory database resets** when the application restarts
* Tables are created automatically by Hibernate using JPA entities
* SQL queries will be printed in the console (for learning purposes)

---

## How to Verify Tables

1. Start the application
2. Open H2 console
3. Login using the details above
4. Click **Connect**
5. Run SQL query:

```sql
SELECT * FROM LEARNER;
```

(Replace `LEARNER` with your actual table name)

---

## Recommended Package Structure

```
com.airtribe.learnermanagementsystem
│
├── controller
├── service
├── repository
├── entity
├── dto
└── exception
```

---

## Learning Purpose

This project is created to understand:

* Spring Boot fundamentals
* REST APIs
* JPA & Hibernate
* Database integration using H2
* Layered architecture

---

You are now ready to use the **H2 Console** and explore your database.
