package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.dto.InstructorDTO;
import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    // http://localhost:9090/cohort/1/instructors
    // Body -> {"instructorName": "Jack","instructorMobile": "12345"}
    @PostMapping("/cohort/{cohortId}/instructors")
    public ResponseEntity<InstructorDTO> createInstructor (@PathVariable("cohortId") Long cohortId,
                                                           @Valid @RequestBody Instructor instructor) throws CohortNotFoundException {
        return instructorService.createInstructor (cohortId, instructor);
    }
}
