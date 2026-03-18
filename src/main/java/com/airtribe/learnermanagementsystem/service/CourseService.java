package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CourseDTO;
import com.airtribe.learnermanagementsystem.entity.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CourseService {

    ResponseEntity<Course> createCourse(Course course);

    List<CourseDTO> getAllCourses();

    CourseDTO getCourseById(Long courseId);

    ResponseEntity<String> deleteCourseById(Long courseId);
}
