package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LearnerService {

    Learner addLearner(Learner learner);

    Learner getLearnerById(Long learnerId) throws LearnerNotFoundException;

    List<Learner> getAllLearners();

    void deleteLearnerById(Long learnerId);

    List<Learner> getLearnerByName(String learnerName);
}
