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
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learnerId;
    private String learnerName;
    public String learnerAddress;

    @ManyToMany(mappedBy = "learners")
    private List<Cohort> cohorts;

}
