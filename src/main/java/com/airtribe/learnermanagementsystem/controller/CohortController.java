package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.service.CohortService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CohortController {

    @Autowired
    CohortService cohortService;

    // http://localhost:9090/cohorts
    // Body -> {"cohortName": "Java", "cohortDescription": "Java BE"}
    @PostMapping("/cohorts")
    public Cohort createCohort (@Valid @RequestBody Cohort cohort) {
        return cohortService.createCohort(cohort);
    }

    // We assume that the Learner and Cohort are already created,
    // and we are mapping one Learner to one Cohort at once.
    // http://localhost:9090/assignLearnerToCohort?learnerId=1&cohortId=1
    @PostMapping("/assignLearnerToCohort")
    public Cohort assignLearnerToCohort (@RequestParam("learnerId") Long learnerId, @RequestParam ("cohortId") Long cohortId) throws CohortNotFoundException, LearnerNotFoundException {
        return cohortService.assignLearnerToCohort(learnerId, cohortId);
    }

    // Parent–Child relationship: Cohort (parent) → Learner (child).
    // This endpoint checks whether learners are present or not.
    // If they are not present, it creates them and assigns the cohort with the given cohortId.
    // If they are present, it assigns them to the cohort for the respective cohortId.
    // http://localhost:9090/cohorts/1/learners (Prerequisite: Cohort os created already)
    // Body -> [{"learnerName": "Jack","learnerAddress": "Pune"}, {"learnerName": "Jane","learnerAddress": "Pune"}]
    @PostMapping("/cohorts/{cohortId}/learners")
    public Cohort createAndAssignLearnersToCohort (@PathVariable("cohortId") Long cohortId, @RequestBody List<Learner> learners) throws CohortNotFoundException {
        return cohortService.createAndAssignLearnersToCohort(cohortId, learners);
    }

    // http://localhost:9090/cohorts/1
    @GetMapping("/cohorts/{id}")
    public Cohort getCohortById (@PathVariable ("id") Long cohortId) throws CohortNotFoundException {
        return cohortService.getCohortById(cohortId);
    }

    // Here the controller gets confused while routing the endpoint to the correct method,
    // which causes ambiguity. We cannot have the same endpoint mapped to multiple methods.
    // http://localhost:9090/cohorts/Java
//    @GetMapping("/cohorts/{name}")
//    public Cohort getCohortName (@PathVariable ("name") String cohortName) {
//        return cohortService.getCohortByName(cohortName);
//    }

    // http://localhost:9090/cohorts
    @GetMapping("/cohorts")
    public List<Cohort> getAllCohorts (@RequestParam (value = "id", required = false) Long id,
                                         @RequestParam (value = "name", required = false) String name) throws CohortNotFoundException {
        if (id != null){
            return List.of(cohortService.getCohortById(id));
        } else if (name != null) {
            return List.of(cohortService.getCohortByName(name));
        }
        return cohortService.getAllCohorts();
    }

    // http://localhost:9090/cohorts/1
    @DeleteMapping("/cohorts/{id}")
    public void deleteCohortById (@PathVariable ("id") Long cohortId) {
        cohortService.deleteCohortById(cohortId);
    }
}
