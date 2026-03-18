package com.airtribe.learnermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private String courseName;
    private String courseDescription;
    private List<CohortDTO> cohorts;
}
