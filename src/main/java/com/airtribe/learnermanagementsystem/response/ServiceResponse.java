package com.airtribe.learnermanagementsystem.response;

import lombok.Data;

@Data
public class ServiceResponse {

    private String rc;
    private String message;

    public ServiceResponse(String rc, String message) {
        this.rc = rc;
        this.message = message;
    }
}
