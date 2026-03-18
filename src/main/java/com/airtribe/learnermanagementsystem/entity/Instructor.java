package com.airtribe.learnermanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "instructor")
    private List<Cohort> cohorts;

}
