package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CourseDTO;
import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.exception.CustomException;
import com.airtribe.learnermanagementsystem.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Mock
    private CourseRepository courseRepository;

    @Test
    public void testCreateCourse() {

        // ARRANGE
        // Step 1: Create a sample Course object (test input)
        Course course = new Course(1L, "Java", "Java BE");

        // Step 2: Mock the repository behavior
        // When save() is called with this course, return the same course object
        when(courseRepository.save(course)).thenReturn(course);

        // ACT
        // Step 3: Call the actual service method
        // This internally calls repository.save()
        Course savedCourse = courseServiceImpl.createCourse(course).getBody();

        // ASSERT
        // Step 4: Verify the result
        // Check if the returned course from service matches the expected course
        Assertions.assertEquals(course, savedCourse);

        // Step 5: Verify interaction with mock
        // Ensure repository.save() was called exactly once with the given course
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    public void testGetCourseByIdPositive() {

        //Arrange
        Course course = new Course(1L, "Java", "Java BE");
        CourseDTO courseDTO = new CourseDTO("Java", "Java BE");
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        //Act
        CourseDTO fetchedCourse = courseServiceImpl.getCourseById(1L);

        //Assertion
        Assertions.assertEquals(courseDTO, fetchedCourse);
    }

    @Test
    public void testGetCourseByIdNegative() {

        //Arrange
        Long courseId = 1L;
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        try {
            //Act
            CourseDTO fetchedCourse = courseServiceImpl.getCourseById(1L);
        } catch (CustomException e) {
            //Assertion
            Assertions.assertEquals("Course not found of id: 1", e.getMessage());
        }


    }
}
