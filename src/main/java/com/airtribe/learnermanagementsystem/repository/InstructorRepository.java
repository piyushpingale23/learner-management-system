package com.airtribe.learnermanagementsystem.repository;

import com.airtribe.learnermanagementsystem.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByInstructorNameAndInstructorMobile(String instructorName, String mobile);

    @Query("SELECT i FROM Instructor i JOIN i.cohorts c WHERE c.cohortId = :cohortId")
    List<Instructor> getInstructorsByCohortId(@Param("cohortId") Long cohortId);
}
