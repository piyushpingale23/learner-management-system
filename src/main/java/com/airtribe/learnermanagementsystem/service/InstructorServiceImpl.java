package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.InstructorDTO;
import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Instructor;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.CustomException;
import com.airtribe.learnermanagementsystem.repository.CohortRepository;
import com.airtribe.learnermanagementsystem.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Override
    public ResponseEntity<InstructorDTO> createInstructor(Long cohortId, Instructor instructor) throws CohortNotFoundException {

        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if (cohortOptional.isEmpty()) {
            throw new CohortNotFoundException("Cohort with id : " + cohortId + " not found!");
        }

        Optional<Instructor> existingInstructor = instructorRepository.findByInstructorNameAndInstructorMobile (instructor.getInstructorName(),
                instructor.getInstructorMobile());
        if (existingInstructor.isPresent()) {
            throw new CustomException("Instructor with name: " + instructor.getInstructorName() + " and mobile: " + instructor.getInstructorMobile() + " exists already!");
        }

        Instructor savedInstructor = instructorRepository.save(instructor);
        cohortRepository.updateInstructorUnderCohort(cohortId, savedInstructor.getInstructorId());

        InstructorDTO instructorDTO = convertInstructorToInstructorDTO(savedInstructor, cohortId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(instructorDTO);
        
    }

    private InstructorDTO convertInstructorToInstructorDTO(Instructor instructor, Long cohortId) {
        InstructorDTO instructorDTO = new InstructorDTO();

        instructorDTO.setInstructorName(instructor.getInstructorName());
        instructorDTO.setInstructorMobile(instructor.getInstructorMobile());
        instructorDTO.setCohortId(cohortId);

        return instructorDTO;
    }
}
