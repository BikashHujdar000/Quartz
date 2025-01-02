package com.example.bikash.Optimized.Quartz;


import com.example.bikash.Optimized.Quartz.Models.JobRequest;
import com.example.bikash.Optimized.Quartz.Response.JobResponse;
import com.example.bikash.Optimized.Quartz.Services.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("dynamic/setup")
public class DynamicControllers {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/sendDynamicJob")

    public ResponseEntity<JobResponse> scheduleJob(@Valid @RequestBody JobRequest jobRequest) {
        JobResponse response =  schedulerService.scheduleJob(jobRequest);
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}




