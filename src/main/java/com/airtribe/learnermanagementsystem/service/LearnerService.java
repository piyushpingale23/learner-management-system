package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.LearnerDTO;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LearnerService {

    ResponseEntity<LearnerDTO> addLearner(Learner learner, Long cohortId);

    LearnerDTO getLearnerById(Long learnerId) throws LearnerNotFoundException;

    List<LearnerDTO> getAllLearners();

    void deleteLearnerById(Long learnerId);

    List<LearnerDTO> getLearnerByName(String learnerName);
}
