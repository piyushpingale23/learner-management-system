package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CourseNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface InstructorService {

    Instructor createInstructor(Long courseId, Instructor instructor) throws CourseNotFoundException;
}
