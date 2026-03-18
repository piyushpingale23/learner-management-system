package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.dto.CourseDTO;
import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    // http://localhost:9090/courses
    // body -> {"courseName": "Java","courseDescription": "Java BE"}
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse (@Valid @RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // http://localhost:9090/courses
    @GetMapping("/courses")
    public List<CourseDTO> getCourses () {
        return courseService.getAllCourses();
    }

    // http://localhost:9090/courses/1
    @GetMapping("/courses/{courseId}")
    public CourseDTO getCourses(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    // http://localhost:9090/courses/1
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourseById(courseId);
    }
}
