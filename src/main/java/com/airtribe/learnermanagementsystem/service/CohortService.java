package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CohortDTO;
import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.request.CohortPaginatedRequest;
import com.airtribe.learnermanagementsystem.request.CohortRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CohortService {

    ResponseEntity<CohortDTO> createCohort(CohortRequest cohort, String courseName);

    Cohort assignLearnerToCohort(Long learnerId, Long cohortId) throws LearnerNotFoundException, CohortNotFoundException;

    CohortDTO getCohortById(Long id) throws CohortNotFoundException;

    CohortDTO getCohortByName(String name);

    List<CohortDTO> getAllCohorts();

    void deleteCohortById(Long cohortId);

    Cohort createAndAssignLearnersToCohort(Long cohortId, List<Learner> learners) throws CohortNotFoundException;

    Page<Cohort> getPaginatedAndSortedCohorts(CohortPaginatedRequest cohortPaginatedRequest);
}
