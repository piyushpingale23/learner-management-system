package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CourseNotFoundException;
import com.airtribe.learnermanagementsystem.repository.CourseRepository;
import com.airtribe.learnermanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Instructor createInstructor(Long courseId, Instructor instructor) throws CourseNotFoundException {

        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new CourseNotFoundException("Course with id : " + courseId + " not found!");
        }

        Optional<Instructor> existingInstructor = instructorRepository.findByInstructorName (instructor.getInstructorName());

        if (existingInstructor.isEmpty()) {
            instructor.setCourse(courseOptional.get());
            return instructorRepository.save(instructor);
        } else {
            existingInstructor.get().setCourse(courseOptional.get());
            return instructorRepository.save(existingInstructor.get());
        }
        
    }
}
