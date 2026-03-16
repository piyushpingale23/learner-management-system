package com.airtribe.learnermanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @NotNull
    @NotEmpty
    private String instructorName;

    @NotNull
    @NotEmpty
    private String instructorMobile;

    @OneToOne
    @NotNull
    private Course course;

    @OneToMany(mappedBy = "instructor")
    private List<Cohort> cohorts;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorMobile() {
        return instructorMobile;
    }

    public void setInstructorMobile(String instructorMobile) {
        this.instructorMobile = instructorMobile;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Cohort> getCohorts() {
        return cohorts;
    }

    public void setCohorts(List<Cohort> cohorts) {
        this.cohorts = cohorts;
    }

    public Instructor(Long instructorId, String instructorName, String instructorMobile, Course course, List<Cohort> cohorts) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorMobile = instructorMobile;
        this.course = course;
        this.cohorts = cohorts;
    }

    public Instructor() {

    }
}
