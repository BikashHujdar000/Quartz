package com.example.bikash.Optimized.Quartz.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {

    private boolean success;

    private String jobId;

    private String jobGroup;

    private String message;

    public JobResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public JobResponse(boolean success, String jobId, String jobGroup, String message) {
        this.success = success;
        this.jobId = jobId;
        this.jobGroup = jobGroup;
        this.message = message;

    }

}
