package com.airtribe.learnermanagementsystem.dto;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import java.util.List;

public class LearnerDTO {

    public LearnerDTO(Long learnerId, String learnerName, String learnerAddress, List<Cohort> cohorts) {
        this.learnerId = learnerId;
        this.learnerName = learnerName;
        this.learnerAddress = learnerAddress;
        this.cohorts = cohorts;
    }

    private Long learnerId;
    private String learnerName;
    public String learnerAddress;
    private List<Cohort> cohorts;

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public String getLearnerAddress() {
        return learnerAddress;
    }

    public void setLearnerAddress(String learnerAddress) {
        this.learnerAddress = learnerAddress;
    }

    public List<Cohort> getCohorts() {
        return cohorts;
    }

    public void setCohorts(List<Cohort> cohorts) {
        this.cohorts = cohorts;
    }
}
