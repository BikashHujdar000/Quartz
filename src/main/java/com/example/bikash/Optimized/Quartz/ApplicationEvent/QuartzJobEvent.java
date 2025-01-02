package com.example.bikash.Optimized.Quartz.ApplicationEvent;

import org.quartz.JobDataMap;
import org.springframework.context.ApplicationEvent;


public class QuartzJobEvent extends ApplicationEvent {

    private final JobDataMap jobDataMap;

    public QuartzJobEvent(Object source, JobDataMap jobDataMap) {
        super(source);
        this.jobDataMap = jobDataMap;
    }

    public JobDataMap getJobDataMap() {
        return jobDataMap;
    }

}
