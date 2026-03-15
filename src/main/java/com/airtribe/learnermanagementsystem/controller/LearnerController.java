package com.airtribe.learnermanagementsystem.controller;

import com.airtribe.learnermanagementsystem.entity.Learner;
import com.airtribe.learnermanagementsystem.exception.LearnerNotFoundException;
import com.airtribe.learnermanagementsystem.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class LearnerController {

    @Autowired
    LearnerService learnerService;

    // http://localhost:9090/learners
    // Body -> {"learnerName": "Jack","learnerAddress": "Pune"}
    @PostMapping("/learners")
    public Learner addLearner (@RequestBody Learner learner) {
        return learnerService.addLearner(learner);
    }

    // http://localhost:9090/learners/1
    @GetMapping("/learners/{id}")
    public Learner getLearnerById (@PathVariable ("id") Long learnerId) throws LearnerNotFoundException {
        return learnerService.getLearnerById(learnerId);
    }

    // Here the controller gets confused while routing the endpoint to the correct method,
    // which causes ambiguity. We cannot have the same endpoint mapped to multiple methods.
    // http://localhost:9090/learners/Piyush
//    @GetMapping("/learner/{name}")
//    public List<Learner> getLearnerByName (@PathVariable ("name") String learnerName) {
//        return learnerService.getLearnerByName(learnerName);
//    }

    // http://localhost:9090/learners?id=1
    // http://localhost:9090/learners?name=Piyush
    @GetMapping("/learners")
    public List<Learner> getAllLearners (@RequestParam (value = "id", required = false) Long id,
                                         @RequestParam (value = "name", required = false) String name) throws LearnerNotFoundException {
        if (id != null){
            return List.of(learnerService.getLearnerById(id));
        } else if (name != null) {
            return learnerService.getLearnerByName(name);
        }
        return learnerService.getAllLearners();
    }

    // http://localhost:9090/learners/1
    @DeleteMapping("/learners/{id}")
    public void deleteLearnerById (@PathVariable ("id") Long learnerId) {
        learnerService.deleteLearnerById(learnerId);
    }

}
