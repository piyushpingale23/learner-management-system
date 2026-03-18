package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CohortDTO;
import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Course;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.CohortNotFoundException;
import com.airtribe.learnermanagementsystem.exception.CustomException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.repository.CohortRepository;
import com.airtribe.learnermanagementsystem.repository.CourseRepository;
import com.airtribe.learnermanagementsystem.repository.LearnerRepository;
import com.airtribe.learnermanagementsystem.request.CohortPaginatedRequest;
import com.airtribe.learnermanagementsystem.request.CohortRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CohortServiceImpl implements CohortService {

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Autowired
    CourseRepository courseRepository;

    @Value("${cohort-page-size}")
    int cohortPageSize;

    @Override
    public ResponseEntity<CohortDTO> createCohort(CohortRequest cohortRequest, String courseName) {

        Optional<Course> optionalCourse = courseRepository.findByCourseName(courseName);
        if (optionalCourse.isEmpty()) {
            throw new CustomException("Course not found with name - " + courseName);
        }

        Optional<Cohort> optionalCohort = cohortRepository.findByCohortName(cohortRequest.getCohortName());
        if (optionalCohort.isPresent()) {
            throw new CustomException("Cohort already exists with name - " + cohortRequest.getCohortName());
        }

        Cohort cohort = new Cohort();
        cohort.setCohortName(cohortRequest.getCohortName());
        cohort.setCohortDescription(cohortRequest.getCohortDescription());
        cohort.setCourse(optionalCourse.get());

        Cohort savedCohort = cohortRepository.save(cohort);
        CohortDTO cohortDTO = convertCohortToCohortDTO(savedCohort);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cohortDTO);
    }

    @Override
    public Cohort assignLearnerToCohort(Long learnerId, Long cohortId) throws LearnerNotFoundException, CohortNotFoundException {

        Optional<Learner> learner = learnerRepository.findById(learnerId);
        if (learner.isEmpty()) {
            throw new LearnerNotFoundException("Learner not found with id " + learnerId);
        }

        Optional<Cohort> cohort = cohortRepository.findById(cohortId);
        if (cohort.isEmpty()) {
            throw new CohortNotFoundException("Cohort not found with id + " + cohortId);
        }

        List<Learner> enrolledLearners = cohort.get().getLearners();
        for (Learner learner1 : enrolledLearners) {
            if (Objects.equals(learner1.getLearnerId(), learnerId)) {
                return cohort.get();
            }
        }

        enrolledLearners.add(learner.get());
        cohort.get().setLearners(enrolledLearners);
        cohortRepository.save(cohort.get());

        return cohort.get();
    }

    @Override
    public CohortDTO getCohortById(Long id) throws CohortNotFoundException {

        CohortDTO cohortDTO;
        if (cohortRepository.findById(id).isPresent()) {
            Cohort cohort = cohortRepository.findById(id).get();
            cohortDTO = convertCohortToCohortDTO(cohort);
        } else {
            throw new CohortNotFoundException("Cohort not found with id : " + id);
        }
        return cohortDTO;
    }


    @Override
    public CohortDTO getCohortByName(String name) {
        Optional<Cohort> cohortOptional = cohortRepository.findByCohortName(name);
        if (cohortOptional.isEmpty()) {
            throw new CustomException("Cohort not found with name - " + name);
        }
        return convertCohortToCohortDTO(cohortOptional.get());
    }

    @Override
    public List<CohortDTO> getAllCohorts() {
        List<Cohort> cohortList = cohortRepository.findAll();
        List<CohortDTO> cohortDTOList = new ArrayList<>();
        for (Cohort cohort : cohortList) {
            CohortDTO cohortDTO = convertCohortToCohortDTO(cohort);
            cohortDTOList.add(cohortDTO);
        }
        return cohortDTOList;
    }

    private CohortDTO convertCohortToCohortDTO(Cohort cohort) {
        CohortDTO cohortDTO = new CohortDTO();
        cohortDTO.setCohortId(cohort.getCohortId());
        cohortDTO.setCohortName(cohort.getCohortName());
        cohortDTO.setCohortDescription(cohort.getCohortDescription());
        cohortDTO.setCourseId(cohort.getCourse().getCourseId());
        return cohortDTO;
    }

    @Override
    public void deleteCohortById(Long cohortId) {
        cohortRepository.deleteById(cohortId);
    }

    @Override
    @Transactional
    public Cohort createAndAssignLearnersToCohort(Long cohortId, List<Learner> learners) throws CohortNotFoundException {

        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if (cohortOptional.isEmpty()) {
            throw new CohortNotFoundException("Cohort not found with id : " + cohortId);
        }

        Cohort cohort = cohortOptional.get();
        List<Learner> managedLearners = new ArrayList<>();

        for (Learner learner : learners) {
            Optional<Learner> existingLearner = learnerRepository.findByLearnerNameAndLearnerAddress(learner.getLearnerName(), learner.getLearnerAddress());

            if (existingLearner.isEmpty()) {
                managedLearners.add(learnerRepository.save(learner));
            } else {
                managedLearners.add(existingLearner.get());
            }
        }

        cohort.getLearners().addAll(managedLearners);
        return cohortRepository.save(cohort);
    }

    @Override
    public Page<Cohort> getPaginatedAndSortedCohorts(CohortPaginatedRequest cohortPaginatedRequest) {

        Pageable pageable = Pageable.ofSize(cohortPageSize);

        return cohortRepository.findAll(pageable);

    }
}
