package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CohortService {

    Cohort createCohort(Cohort cohort);

    Cohort assignLearnerToCohort(Long learnerId, Long cohortId) throws LearnerNotFoundException, CohortNotFoundException;

    Cohort getCohortById(Long id) throws CohortNotFoundException;

    Cohort getCohortByName(String name);

    List<Cohort> getAllCohorts();

    void deleteCohortById(Long cohortId);
}
