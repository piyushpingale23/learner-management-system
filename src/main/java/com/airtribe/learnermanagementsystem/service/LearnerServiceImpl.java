package com.airtribe.learnermanagementsystem.service;

import com.airtribe.learnermanagementsystem.dto.LearnerDTO;
import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearnerServiceImpl implements LearnerService{

    @Autowired
    LearnerRepository learnerRepository;

    @Override
    public Learner addLearner(Learner learner) {
        return learnerRepository.save(learner);
    }

    @Override
    public LearnerDTO getLearnerById(Long learnerId) throws LearnerNotFoundException {

        LearnerDTO learnerDTO = new LearnerDTO();
        if (learnerRepository.findById(learnerId).isPresent()) {
            Learner learner = learnerRepository.findById(learnerId).get();

            learnerDTO.setLearnerId(learner.getLearnerId());
            learnerDTO.setLearnerName(learner.getLearnerName());
            learnerDTO.setLearnerAddress(learner.learnerAddress);

        } else {
            throw new LearnerNotFoundException("Learner Not found with Id : " + learnerId);
        }
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
