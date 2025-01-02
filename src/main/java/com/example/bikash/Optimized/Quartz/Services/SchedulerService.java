package com.example.bikash.Optimized.Quartz.Services;

import com.example.bikash.Optimized.Quartz.Jobs.QuartzZobExecution;
import com.example.bikash.Optimized.Quartz.Models.JobRequest;
import com.example.bikash.Optimized.Quartz.Response.JobResponse;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;


@Service
@Slf4j
public class SchedulerService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleBuilderService scheduleBuilderService;

    public JobResponse scheduleJob(JobRequest jobRequest) {

        try {
            JobDetail jobDetail = null;
            Trigger trigger = null;
            ZonedDateTime dateTime = ZonedDateTime.of(jobRequest.getDateTime(), jobRequest.getTimeZone());
            jobDetail = scheduleBuilderService.buildDynamicJobDetail(jobRequest, QuartzZobExecution.class);

            if (jobRequest.isRecurring()) {
                trigger = scheduleBuilderService.buildTriggerEveryDay(jobDetail, jobRequest);
            } else {
                trigger = scheduleBuilderService.buildTrigger(jobDetail, dateTime);
            }

            scheduler.scheduleJob(jobDetail, trigger);

            log.info("Job with id :{} scheduled successfully. with a trigger time {}", jobDetail.getKey().getName(),trigger.getStartTime());
            JobResponse jobResponse = new JobResponse(true, jobDetail.getKey().getName(),
                    jobDetail.getKey().getGroup(), "Job scheduled successfully.");
            return jobResponse;

        } catch (SchedulerException e) {
            log.error("Failed to schedule job", e);
            JobResponse jobResponse = new JobResponse(false, "Error while scheduling, please try again.");
            return jobResponse;
        }

    }


}
