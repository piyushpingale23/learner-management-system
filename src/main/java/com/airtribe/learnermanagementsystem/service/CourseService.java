package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {

    Course createCourse(Course course);
}
