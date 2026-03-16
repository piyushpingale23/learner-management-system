package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CourseNotFoundException;
import com.airtribe.learnermanagementsystem.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    // http://localhost:9090/course/1/instructors
    // Body -> {"instructorName": "Jack","instructorMobile": "12345"}
    @PostMapping("/course/{courseId}/instructors")
    public Instructor createInstructor (@Valid @PathVariable("courseId") Long courseId, @RequestBody Instructor instructor) throws CourseNotFoundException {
        return instructorService.createInstructor (courseId, instructor);
    }
}
