package com.airtribe.learnermanagementsystem.exception;

import com.airtribe.learnermanagementsystem.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity<ServiceResponse> handleLearnerNotFound (LearnerNotFoundException e) {
        ServiceResponse response = new ServiceResponse("Not Found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
