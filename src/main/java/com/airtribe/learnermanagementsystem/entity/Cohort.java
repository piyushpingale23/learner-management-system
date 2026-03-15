package com.airtribe.learnermanagementsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cohortId;

    private String cohortName;

    private String cohortDescription;

    @ManyToMany
    private List<Learner> learners;

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learnerList) {
        this.learners = learnerList;
    }

    public Long getCohortId() {
        return cohortId;
    }

    public void setCohortId(Long cohortId) {
        this.cohortId = cohortId;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getCohortDescription() {
        return cohortDescription;
    }

    public void setCohortDescription(String cohortDescription) {
        this.cohortDescription = cohortDescription;
    }
}
