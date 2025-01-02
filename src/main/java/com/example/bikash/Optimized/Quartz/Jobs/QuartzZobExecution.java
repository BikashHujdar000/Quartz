package com.example.bikash.Optimized.Quartz.Jobs;

import com.example.bikash.Optimized.Quartz.ApplicationEvent.QuartzJobEvent;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
public class QuartzZobExecution implements BaseJob {

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getMergedJobDataMap();

        log.info("publishing Event");
        System.out.println("publishing event after the job is triggered ");

        QuartzJobEvent quartzJobEvent = new QuartzJobEvent(this, jobDataMap);

        eventPublisher.publishEvent(quartzJobEvent);


    }
}
