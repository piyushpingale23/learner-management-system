package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LearnerServiceImpl implements LearnerService{

    @Autowired
    LearnerRepository learnerRepository;

    @Override
    public Learner addLearner(Learner learner) {
        return learnerRepository.save(learner);
    }

    @Override
    public Learner getLearnerById(Long learnerId) throws LearnerNotFoundException {

        Learner learner;
        if (learnerRepository.findById(learnerId).isPresent()) {
            learner = learnerRepository.findById(learnerId).get();
        } else {
            throw new LearnerNotFoundException("Learner Not found with Id : " + learnerId);
        }
        return learner;
    }

    @Override
    public List<Learner> getAllLearners() {
        return learnerRepository.findAll();
    }

    @Override
    public void deleteLearnerById(Long learnerId) {
        learnerRepository.deleteById(learnerId);
    }

    @Override
    public List<Learner> getLearnerByName(String learnerName) {
        return learnerRepository.findByLearnerName(learnerName);
    }
}
