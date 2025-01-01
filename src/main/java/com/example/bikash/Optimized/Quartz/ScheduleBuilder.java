package com.example.bikash.Optimized.Quartz;

import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class ScheduleBuilder {

    public JobDetail buildJobDetail(EmailRequest scheeduledEmailRequest) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", scheeduledEmailRequest.getEmail());
        jobDataMap.put("subject", scheeduledEmailRequest.getSubject());
        jobDataMap.put("body", scheeduledEmailRequest.getBody());
        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-job")
                .withDescription("send emailJob")
                .usingJobData(jobDataMap)
                .storeDurably(true)
                .requestRecovery(true)
                .build();
    }

    public Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt) {

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-trigger")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow())  // Use this for better handling after missed executions
                .build();
    }
}