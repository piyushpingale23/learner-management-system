package com.airtribe.learnermanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cohort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cohortId;

    @Column(nullable = false)
    private String cohortName;

    private String cohortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToMany
    List<Learner> learners;

    @ManyToOne
    private Instructor instructor;

}