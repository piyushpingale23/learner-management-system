package com.airtribe.learnermanagementsystem.dto;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.entity.Learner;
import java.util.List;

public class DtoConverter {

    public static CohortDTO convertCohortToCohortDTO(Cohort cohort) {

        CohortDTO cohortDTO = new CohortDTO();
        cohortDTO.setCohortId(cohort.getCohortId());
        cohortDTO.setCohortName(cohort.getCohortName());
        cohortDTO.setCohortDescription(cohort.getCohortDescription());
        cohortDTO.setCourseId(cohort.getCourse().getCourseId());

        return cohortDTO;
    }

    public static LearnerDTO convertLearnerToLearnerDTO(Learner savedLearner, CohortDTO cohortDTO) {

        LearnerDTO learnerDTO = new LearnerDTO();
        learnerDTO.setLearnerName(savedLearner.getLearnerName());
        learnerDTO.setLearnerAddress(savedLearner.getLearnerAddress());
        learnerDTO.setCohorts(List.of(cohortDTO));

        return learnerDTO;
    }

    public static CourseDTO convertCourseToCourseDTO(Course course, List<CohortDTO> cohortDTOList) {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setCourseDescription(course.getCourseDescription());
        courseDTO.setCohorts(cohortDTOList);

        return courseDTO;
    }
}
