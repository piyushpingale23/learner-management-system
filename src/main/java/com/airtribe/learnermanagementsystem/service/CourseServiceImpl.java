package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CohortDTO;
import com.airtribe.learnermanagementsystem.dto.CourseDTO;
import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CustomException;
import com.airtribe.learnermanagementsystem.repository.CohortRepository;
import com.airtribe.learnermanagementsystem.repository.CourseRepository;
import com.airtribe.learnermanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public ResponseEntity<Course> createCourse(Course course) {

        Optional<Course> optionalCourse = courseRepository.findByCourseName (course.getCourseName());

        if (optionalCourse.isPresent()) {
            throw new CustomException("Course already present wih name: " + course.getCourseName());
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseRepository.save(course));
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> courseList = courseRepository.findAll();
        if (courseList.isEmpty()) {
            throw new CustomException("Do not have any course, please onboard the course!");
        }

        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {

            List<CohortDTO> cohortDTOList = getCohortsUnderCourse(course.getCourseId());

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCohorts(cohortDTOList);
            courseDTOList.add(courseDTO);
        }

        return courseDTOList;

    }

    @Override
    public CourseDTO getCourseById(Long courseId) {

        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isEmpty()){
            throw new CustomException("Course not found of id: " + courseId);
        }
        List<CohortDTO> cohortDTOList = getCohortsUnderCourse(courseId);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName(courseOptional.get().getCourseName());
        courseDTO.setCourseDescription(courseOptional.get().getCourseDescription());
        courseDTO.setCohorts(cohortDTOList);

        return courseDTO;

    }

    private List<CohortDTO> getCohortsUnderCourse(Long courseId) {

        List<Cohort> cohortList = cohortRepository.getCohortsByCourseId(courseId);
        List<CohortDTO> cohortDTOList = new ArrayList<>();
        cohortList.forEach(cohort -> {
            CohortDTO cohortDTO = new CohortDTO();
            cohortDTO.setCohortId(cohort.getCohortId());
            cohortDTO.setCohortName(cohort.getCohortName());
            cohortDTO.setCohortDescription(cohort.getCohortDescription());
            cohortDTOList.add(cohortDTO);
        });

        return cohortDTOList;
    }

    @Override
    public ResponseEntity<String> deleteCourseById(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isEmpty()){
            throw new CustomException("Course not found of id: " + courseId);
        }

        List<Cohort> cohortList =  cohortRepository.getCohortsByCourseId(courseId);
        if (!cohortList.isEmpty()) {
            throw new CustomException("You cannot delete the course as cohorts are assigned to it.");
        }

        courseRepository.delete(courseOptional.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Course deleted successfully!");

    }
}
