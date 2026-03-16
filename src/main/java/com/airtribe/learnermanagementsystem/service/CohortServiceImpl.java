package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.repository.CohortRepository;
import com.airtribe.learnermanagementsystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CohortServiceImpl implements CohortService {

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Override
    public Cohort createCohort(Cohort cohort) {
        return cohortRepository.save(cohort);
    }

    @Override
    public Cohort assignLearnerToCohort(Long learnerId, Long cohortId) throws LearnerNotFoundException, CohortNotFoundException {

        Optional<Learner> learner = learnerRepository.findById(learnerId);
        if (learner.isEmpty()) {
            throw new LearnerNotFoundException("Learner not found with id " + learnerId);
        }

        Optional<Cohort> cohort = cohortRepository.findById(cohortId);
        if (cohort.isEmpty()) {
            throw new CohortNotFoundException("Cohort not found with id + " + cohortId);
        }

        List<Learner> enrolledLearners = cohort.get().getLearners();
        for (Learner learner1 : enrolledLearners) {
            if (Objects.equals(learner1.getLearnerId(), learnerId)) {
                return cohort.get();
            }
        }

        enrolledLearners.add(learner.get());
        cohort.get().setLearners(enrolledLearners);
        cohortRepository.save(cohort.get());

        return cohort.get();
    }

    @Override
    public Cohort getCohortById(Long id) throws CohortNotFoundException {

        Cohort cohort;
        if (cohortRepository.findById(id).isPresent()) {
            cohort = cohortRepository.findById(id).get();
        } else {
            throw new CohortNotFoundException("Cohort not found with id : " + id);
        }
        return cohort;
    }

    @Override
    public Cohort getCohortByName(String name) {
        return cohortRepository.findByCohortName(name);
    }

    @Override
    public List<Cohort> getAllCohorts() {
        return cohortRepository.findAll();
    }

    @Override
    public void deleteCohortById(Long cohortId) {
        cohortRepository.deleteById(cohortId);
    }

    @Override
    @Transactional
    public Cohort createAndAssignLearnersToCohort(Long cohortId, List<Learner> learners) throws CohortNotFoundException {

        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if (cohortOptional.isEmpty()) {
            throw new CohortNotFoundException("Cohort not found with id : " + cohortId);
        }

        Cohort cohort = cohortOptional.get();
        List<Learner> managedLearners = new ArrayList<>();

        for (Learner learner : learners) {
            Learner existingLearner = learnerRepository.findByLearnerNameAndLearnerAddress(learner.getLearnerName(), learner.getLearnerAddress());

            if (existingLearner == null) {
                managedLearners.add(learnerRepository.save(learner));
            } else {
                managedLearners.add(existingLearner);
            }
        }

        cohort.getLearners().addAll(managedLearners);
        return cohortRepository.save(cohort);
    }
}
