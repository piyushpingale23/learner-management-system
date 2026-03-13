package com.airtribe.learnermanagementsystem.response;

import lombok.Data;

@Data
public class ServiceResponse {

    private String status;
    private String message;

    public ServiceResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
