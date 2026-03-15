package com.airtribe.learnermanagementsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long learnerId;

    public String learnerName;

    public String learnerAddress;

    @ManyToMany(mappedBy = "learners")
    private List<Cohort> cohorts;

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }

    public String getLearnerAddress() {
        return learnerAddress;
    }

    public void setLearnerAddress(String learnerAddress) {
        this.learnerAddress = learnerAddress;
    }

}
