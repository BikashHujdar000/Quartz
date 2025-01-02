package com.example.bikash.Optimized.Quartz.ApplicationEvent;

import org.quartz.JobDataMap;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobEventListener {

    @EventListener
    public void handleJobDataMapEvent(QuartzJobEvent event) {

        //retrieving the jobDataMap form the event

        JobDataMap jobDataMap = event.getJobDataMap();

        // Log or process the JobDataMap
        System.out.println("Received QuartzJobEvent with data:");

        jobDataMap.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });

    }
}

