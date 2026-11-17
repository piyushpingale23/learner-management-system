package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.CohortDTO;
import com.airtribe.learnermanagementsystem.dto.LearnerDTO;
import com.airtribe.learnermanagementsystem.entity.Cohort;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.CustomException;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.repository.CohortRepository;
import com.airtribe.learnermanagementsystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.airtribe.learnermanagementsystem.dto.DtoConverter.convertCohortToCohortDTO;
import static com.airtribe.learnermanagementsystem.dto.DtoConverter.convertLearnerToLearnerDTO;

@Service
public class LearnerServiceImpl implements LearnerService{

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    CohortRepository cohortRepository;

    @Override
    public ResponseEntity<LearnerDTO> addLearner(Learner learner, Long cohortId) {

        Optional<Cohort> optionalCohort = cohortRepository.findById(cohortId);
        if (optionalCohort.isEmpty()) {
            throw new CustomException("Cohort not found with id - " + cohortId);
        }

        Learner savedLearner = learnerRepository.save(learner);

        optionalCohort.get().getLearners().add(savedLearner);
        cohortRepository.save(optionalCohort.get());

        CohortDTO cohortDTO = convertCohortToCohortDTO (optionalCohort.get());
        LearnerDTO learnerDTO = convertLearnerToLearnerDTO (savedLearner, cohortDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(learnerDTO);
    }

    @Override
    public LearnerDTO getLearnerById(Long learnerId) throws LearnerNotFoundException {

        Optional<Learner> optionalLearner = learnerRepository.findById(learnerId);

        if (optionalLearner.isEmpty()) {
            throw new LearnerNotFoundException("Learner Not found with Id : " + learnerId);
        }

        LearnerDTO learnerDTO = new LearnerDTO();

        learnerDTO.setLearnerId(optionalLearner.get().getLearnerId());
        learnerDTO.setLearnerName(optionalLearner.get().getLearnerName());
        learnerDTO.setLearnerAddress(optionalLearner.get().learnerAddress);

        return learnerDTO;
    }

    @Override
    public List<LearnerDTO> getAllLearners() {
        List<LearnerDTO> learnerDTOList = new ArrayList<>();
        List<Learner> learnerList = learnerRepository.findAll();

        for (Learner learner : learnerList){
            LearnerDTO learnerDTO = new LearnerDTO();

            learnerDTO.setLearnerId(learner.getLearnerId());
            learnerDTO.setLearnerName(learner.getLearnerName());
            learnerDTO.setLearnerAddress(learner.learnerAddress);
            learnerDTOList.add(learnerDTO);
        }

        return learnerDTOList;
    }

    @Override
    public void deleteLearnerById(Long learnerId) {
        learnerRepository.deleteById(learnerId);
    }

    @Override
    public List<LearnerDTO> getLearnerByName(String learnerName) {
        List<LearnerDTO> learnerDTOList = new ArrayList<>();

        List<Learner> learnerList = learnerRepository.findByLearnerName(learnerName);

        for (Learner learner : learnerList){
            LearnerDTO learnerDTO = new LearnerDTO();

            learnerDTO.setLearnerId(learner.getLearnerId());
            learnerDTO.setLearnerName(learner.getLearnerName());
            learnerDTO.setLearnerAddress(learner.learnerAddress);
            learnerDTOList.add(learnerDTO);
        }

        return learnerDTOList;
    }
}
