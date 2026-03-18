package com.airtribe.learnermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {

    private Long instructorId;
    private String instructorName;
    private String instructorMobile;
    private Long cohortId;

}
