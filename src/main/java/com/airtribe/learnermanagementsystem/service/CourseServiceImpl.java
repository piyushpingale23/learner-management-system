package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
