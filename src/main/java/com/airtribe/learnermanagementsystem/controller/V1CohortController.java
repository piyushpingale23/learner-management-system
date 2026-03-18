package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.request.CohortPaginatedRequest;
import com.airtribe.learnermanagementsystem.service.CohortService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class V1CohortController {

    @Autowired
    CohortService cohortService;

    // backward compatible, versioned, paginated, sorted api
    @GetMapping("/v1/cohorts")
    public Page<Cohort> getAllCohorts (@Valid @RequestBody CohortPaginatedRequest cohortPaginatedRequest) {

        if (!cohortPaginatedRequest.getSortBy().equals("cohortId") && !cohortPaginatedRequest.getSortBy().equals("cohortName")) {
            cohortPaginatedRequest.setSortBy("cohortId");
        }

        if (!cohortPaginatedRequest.getSortDir().equals("asc") && !cohortPaginatedRequest.getSortDir().equals("desc")) {
            cohortPaginatedRequest.setSortDir("asc");
        }

        return cohortService.getPaginatedAndSortedCohorts (cohortPaginatedRequest);

    }
}
