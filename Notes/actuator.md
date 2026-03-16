# Spring Boot Actuator Guide

## 1. Introduction

**Spring Boot Actuator** is a module of Spring Boot that provides **production-ready monitoring and management features** for your application.

It helps developers and system administrators monitor the internal state of a running application.

With Actuator, you can inspect:

* Application health
* JVM metrics
* HTTP requests
* Spring Beans
* Application configuration
* Environment properties
* Thread information

Actuator exposes this information using **HTTP endpoints**.

Example:

```
http://localhost:9090/actuator
```

---

# 2. Why Actuator is Used

In real production systems we need to know:

* Is the application running?
* Is the database connected?
* How much memory is used?
* How many threads are active?
* What beans are loaded?
* What REST endpoints exist?

Actuator provides this information through **REST endpoints**.

---

# 3. How to Enable Actuator

## Step 1 — Add Dependency (Gradle)

Add the following dependency in `build.gradle`:

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
}
```

---

## Step 2 — Configure Application Properties

Add these properties in `application.properties`:

```properties
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```

Explanation:

* `exposure.include=*` → exposes all actuator endpoints
* `health.show-details=always` → shows detailed health information

Restart the application after adding these properties.

---

# 4. Actuator Base Endpoint

Open the base actuator endpoint:

```
http://localhost:9090/actuator
```

This endpoint lists all available actuator endpoints.

Example response:

```
health
metrics
beans
env
mappings
info
threaddump
```

---

# 5. Important Actuator Endpoints

## 5.1 Health Endpoint

```
/actuator/health
```

Purpose: Shows whether the application is **UP or DOWN**.

Example:

```json
{
 "status": "UP"
}
```

If something fails (like DB connection):

```json
{
 "status": "DOWN"
}
```

---

## 5.2 Metrics Endpoint

```
/actuator/metrics
```
## 5.2 Metrics Endpoint

The **Metrics Endpoint** in Spring Boot Actuator provides information about the **performance and behavior of the running application and the system where it runs**.

You can access the metrics endpoint using:

```
http://localhost:8080/actuator/metrics
```

This endpoint lists all available metrics in the application.

Metrics are generally divided into two main categories:

1. **System Metrics**
2. **Application Metrics**

---

## 5.2.1 System Metrics

### Definition

**System Metrics** provide information about the **underlying system and runtime environment where the application is running**.

These metrics are mainly related to:

* JVM
* CPU
* Memory
* Threads
* Garbage Collection
* Operating System

System metrics help developers and operations teams monitor the **health, performance, and resource usage of the system running the application**.

---

### Key Areas Covered by System Metrics

#### JVM Metrics

Metrics related to the **Java Virtual Machine**.

Examples:

```
jvm.memory.used
jvm.memory.max
```

| Metric          | Description                                |
|-----------------|--------------------------------------------|
| jvm.memory.used | Amount of memory currently used by the JVM |
| jvm.memory.max  | Maximum memory available to the JVM        |

---

#### Thread Metrics

Metrics related to **threads running inside the JVM**.

Examples:

```
jvm.threads.live
jvm.threads.daemon
```

| Metric             | Description                        |
|--------------------|------------------------------------|
| jvm.threads.live   | Number of currently active threads |
| jvm.threads.daemon | Number of daemon threads running   |

---

#### CPU Metrics

Metrics that show **CPU utilization**.

Examples:

```
process.cpu.usage
system.cpu.usage
```

| Metric            | Description                                  |
|-------------------|----------------------------------------------|
| process.cpu.usage | CPU usage of the current application process |
| system.cpu.usage  | CPU usage of the entire system               |

---

#### Garbage Collection Metrics

Metrics related to **JVM Garbage Collection activity**.

Examples:

```
jvm.gc.memory.allocated
jvm.gc.memory.promoted
```

| Metric                  | Description                                             |
|-------------------------|---------------------------------------------------------|
| jvm.gc.memory.allocated | Memory allocated during GC cycles                       |
| jvm.gc.memory.promoted  | Memory promoted from young generation to old generation |

---

### Summary of Common System Metrics

```
jvm.memory.used
jvm.memory.max
jvm.threads.live
jvm.threads.daemon
process.cpu.usage
system.cpu.usage
jvm.gc.memory.allocated
jvm.gc.memory.promoted
```

These metrics help monitor **system health and JVM performance**.

---

## 5.2.2 Application Metrics

### Definition

**Application Metrics** measure **what the application itself is doing**.

These metrics track information related to:

* HTTP requests
* API performance
* Error rates
* Database usage
* Business operations
* Server session activity

Application metrics help developers understand **how the application behaves under real usage**.

---

### Key Areas Covered by Application Metrics

#### HTTP Request Metrics

These metrics track **incoming API requests and their performance**.

Example:

```
http.server.requests
```

| Metric               | Description                                                |
|----------------------|------------------------------------------------------------|
| http.server.requests | Tracks request count, response time, and HTTP status codes |

---

#### Database Metrics

If the application uses a database connection pool, Actuator exposes metrics related to **database connections**.

Examples:

```
jdbc.connections.active
jdbc.connections.max
```

| Metric                  | Description                           |
|-------------------------|---------------------------------------|
| jdbc.connections.active | Number of active database connections |
| jdbc.connections.max    | Maximum allowed database connections  |

---

#### Web Server Metrics

When using an embedded server like **Tomcat**, Actuator exposes session-related metrics.

Examples:

```
tomcat.sessions.active.current
tomcat.sessions.active.max
```

| Metric                         | Description                            |
|--------------------------------|----------------------------------------|
| tomcat.sessions.active.current | Current number of active user sessions |
| tomcat.sessions.active.max     | Maximum number of sessions observed    |

---

### Summary of Common Application Metrics

```
http.server.requests
jdbc.connections.active
jdbc.connections.max
tomcat.sessions.active.current
tomcat.sessions.active.max
```

These metrics help monitor **application behavior and performance**.

---

## 5.2.3 Comparison Between System and Application Metrics

| Feature    | System Metrics              | Application Metrics          |
|------------|-----------------------------|------------------------------|
| Focus      | System & JVM performance    | Application behavior         |
| Related To | CPU, Memory, JVM, Threads   | APIs, Database, Sessions     |
| Purpose    | Monitor runtime environment | Monitor application activity |
| Example    | jvm.memory.used             | http.server.requests         |

---

## 5.2.4 Practical Usage

In production systems, these metrics are usually collected and visualized using monitoring tools such as:

* Prometheus
* Grafana

These tools provide **real-time dashboards** for monitoring application and system performance.

Typical dashboards display:

* CPU usage
* Memory usage
* Request rate
* Error rate
* JVM metrics
* Database connection usage


---

## 5.3 Beans Endpoint

```
/actuator/beans
```

Shows all **Spring Beans** currently loaded in the application.

Useful for debugging dependency injection.

---

## 5.4 Environment Endpoint

```
/actuator/env
```

Displays **environment variables and configuration properties**.

Example:

```
server.port
spring.datasource.url
spring.profiles.active
```

---

## 5.5 Mappings Endpoint

```
/actuator/mappings
```

Displays all **REST API mappings** in your application.

Example:

```
GET /users
POST /users
GET /orders
```

---

## 5.6 Thread Dump

```
/actuator/threaddump
```

Shows all **JVM threads currently running**.

Example threads:

```
main
http-nio-9090-exec-1
Reference Handler
Finalizer
```

Useful for diagnosing **thread blocking or deadlocks**.

---

## 5.7 Info Endpoint

```
/actuator/info
```

Displays custom application information.

You can configure it in properties:

```properties
info.app.name=Order Service
info.app.version=1.0
```

Example response:

```json
{
 "app": {
   "name": "Order Service",
   "version": "1.0"
 }
}
```

---

# 6. Actuator Metrics Example

Example endpoint:

```
/actuator/metrics/jvm.memory.used
```

Response:

```json
{
 "name": "jvm.memory.used",
 "measurements": [
   {
     "statistic": "VALUE",
     "value": 35681280
   }
 ]
}
```

Meaning:

The JVM is currently using approximately **34MB of memory**.

---

# 7. How to Understand Actuator Metrics

Best learning approach:

### Step 1

Open:

```
/actuator/metrics
```

### Step 2

Select any metric.

Example:

```
jvm.threads.live
```

### Step 3

Open detailed metric:

```
/actuator/metrics/jvm.threads.live
```

Gradually learn the meaning of common metrics.

Common metrics:

| Metric               | Meaning                  |
|----------------------|--------------------------|
| jvm.memory.used      | memory currently used    |
| jvm.memory.max       | maximum available memory |
| jvm.threads.live     | number of active threads |
| process.cpu.usage    | CPU utilization          |
| http.server.requests | total HTTP requests      |

---

# 8. Production Monitoring Tools

Actuator is often integrated with monitoring tools such as:

* Prometheus
* Grafana

These tools collect Actuator metrics and display them as **live dashboards**.

Example dashboards show:

* CPU usage
* memory consumption
* request rate
* error rate
* JVM performance

---

# 9. Security Consideration

In production systems, exposing all endpoints is not recommended.

Example safer configuration:

```properties
management.endpoints.web.exposure.include=health,info
```

This exposes only the most important endpoints.

---

# 10. Best Way to Learn Actuator

Follow this practical learning approach:

1. Enable Actuator in a Spring Boot project.
2. Open `/actuator`.
3. Explore all endpoints.
4. Inspect responses carefully.
5. Search and learn what each metric represents.

Within a few days you will understand most Actuator features.

---

# 11. Summary

| Feature     | Purpose                                     |
|-------------|---------------------------------------------|
| Actuator    | Monitor and manage Spring Boot applications |
| /actuator   | Lists available endpoints                   |
| /health     | Application health status                   |
| /metrics    | JVM and application metrics                 |
| /beans      | Lists all Spring Beans                      |
| /env        | Environment and configuration properties    |
| /mappings   | Lists REST API endpoints                    |
| /threaddump | Displays JVM thread information             |

---

# Conclusion

Spring Boot Actuator is an essential tool for monitoring, debugging, and managing applications in both development and production environments. By exposing operational information through REST endpoints, it allows developers and operations teams to quickly understand the internal state and performance of a running application.
