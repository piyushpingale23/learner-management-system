package com.airtribe.learnermanagementsystem.repository;

import com.airtribe.learnermanagementsystem.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LearnerRepository extends JpaRepository<Learner, Long> {

    List<Learner> findByLearnerName(String learnerName);

    Learner findByLearnerNameAndLearnerAddress(String learnerName, String learnerAddress);
}
