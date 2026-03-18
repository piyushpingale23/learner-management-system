package com.airtribe.learnermanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LearnerDTO {

    private Long learnerId;
    private String learnerName;
    public String learnerAddress;
    private List<CohortDTO> cohorts;

}
