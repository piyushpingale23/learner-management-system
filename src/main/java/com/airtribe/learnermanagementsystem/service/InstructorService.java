package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.InstructorDTO;
import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface InstructorService {

    ResponseEntity<InstructorDTO> createInstructor(Long cohortId, Instructor instructor) throws CohortNotFoundException;
}
