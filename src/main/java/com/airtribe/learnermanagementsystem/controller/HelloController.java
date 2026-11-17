package com.airtribe.learnermanagementsystem.controller;

/*
 * Dependency Injection (DI)
 * -------------------------
 * Dependency Injection means providing the required dependency (bean)
 * to a class instead of the class creating the object using 'new'.
 *
 * Example:
 * HelloController depends on HelloService.
 *
 * HelloController --> Dependent Class (Client)
 * HelloService    --> Dependency Bean
 *
 * Spring creates and manages both beans in the IoC Container.
 * Spring then injects the HelloService bean into the HelloController bean.
 */

import com.airtribe.learnermanagementsystem.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Field Injection
 * ----------------
 * Spring injects the dependency directly into the field.
 *
 * HelloController requires HelloService to perform its work.
 *
 * During bean creation:
 * 1. Spring creates the HelloService bean.
 * 2. Spring creates the HelloController bean.
 * 3. Spring injects the HelloService bean into the helloService field.
 *
 * Injection Point = Field
 *
 * Note:
 * Field Injection uses @Autowired on a member variable.
 */

//@RestController
//public class HelloController {
//
//    @Autowired
//    private HelloService helloService;
//
//    @GetMapping("/hello")
//    public String helloLMS() {
//        return helloService.helloLms();
//    }
//}

/*
 * Constructor Injection
 * ---------------------
 * Spring injects the dependency through the constructor.
 *
 * HelloController requires HelloService to perform its work.
 *
 * During bean creation:
 * 1. Spring creates the HelloService bean.
 * 2. Spring finds that HelloController has a constructor
 *    that requires a HelloService object.
 * 3. Spring retrieves the HelloService bean from the IoC Container.
 * 4. Spring passes the HelloService bean as an argument
 *    to the HelloController constructor.
 * 5. The constructor stores the dependency in the helloService field.
 *
 * Injection Point = Constructor
 *
 * Note:
 * Constructor Injection uses constructor parameters
 * instead of @Autowired on fields.
 *
 * Since Spring 4.3, if a class has only one constructor,
 * @Autowired on the constructor is optional.
 *
 * Advantages:
 * - Dependencies are mandatory.
 * - Fields can be declared final.
 * - Easier unit testing.
 * - Recommended by Spring.
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String helloLMS() {
        return helloService.helloLms();
    }
}