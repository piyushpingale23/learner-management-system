package com.airtribe.learnermanagementsystem.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CohortPaginatedRequest {

    @NotNull
    @Positive
    private int pageNumber;

    private String sortBy;

    private String sortDir;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }
}
