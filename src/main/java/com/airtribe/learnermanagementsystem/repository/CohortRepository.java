package com.airtribe.learnermanagementsystem.repository;

import com.airtribe.learnermanagementsystem.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {

    Optional<Cohort> findByCohortName(String name);

    @Query(value = "SELECT c FROM Cohort c WHERE c.course.courseId = :courseId")
    List<Cohort> getCohortsByCourseId(Long courseId);

    @Modifying
    @Transactional
    @Query("UPDATE Cohort c SET c.instructor.instructorId = :instructorId WHERE c.cohortId = :cohortId")
    int updateInstructorUnderCohort(@Param("cohortId") Long cohortId,
                                    @Param("instructorId") Long instructorId);
}
