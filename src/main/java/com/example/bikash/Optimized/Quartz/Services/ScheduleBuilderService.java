package com.example.bikash.Optimized.Quartz.Services;

import com.example.bikash.Optimized.Quartz.Models.JobRequest;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class ScheduleBuilderService {

    public JobDetail buildDynamicJobDetail(JobRequest jobRequest, Class<? extends Job> jobClass) {
        JobDataMap jobDataMap = new JobDataMap(jobRequest.getJobData());

        return JobBuilder.newJob(jobClass)
                .withIdentity(UUID.randomUUID().toString(), jobRequest.getJobGroup())
                .withDescription("Dynamic Job of type: " + jobRequest.getJobType())
                .usingJobData(jobDataMap)
                .storeDurably(true)
                .requestRecovery(true)
                .build();
    }

    public Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup() + "-trigger")
                .withDescription("Trigger for job: " + jobDetail.getKey().getName())
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withMisfireHandlingInstructionFireNow())
                .build();
    }

    public Trigger buildTriggerEveryDay(JobDetail jobDetail, JobRequest jobRequest) {

        //default setting the time at 1 00 am if not provided  time
        LocalDateTime dateTime = jobRequest.getDateTime() != null ? jobRequest.getDateTime()
                : LocalDateTime.now().withHour(1).withMinute(0).withSecond(0).withNano(0);

        // adjust the date of quartz scheduling time if time is before from the user
        if (dateTime.isBefore(LocalDateTime.now())) {
            dateTime = dateTime.plusDays(1);
        }

        Date startTime = Date.from(dateTime.atZone(jobRequest.getTimeZone()).toInstant());

        // Determine interval unit and value based on TimeStatus
        int unitTime = jobRequest.getUnitTime() > 0 ? jobRequest.getUnitTime() : 1; // Default to 1 if not provided
        DateBuilder.IntervalUnit intervalUnit;

        switch (jobRequest.getStatus()) {
            case EVERY_MINUTE:
                intervalUnit = DateBuilder.IntervalUnit.MINUTE;
                break;
            case EVERY_HOUR:
                intervalUnit = DateBuilder.IntervalUnit.HOUR;
                break;
            case EVERY_DAY:
                intervalUnit = DateBuilder.IntervalUnit.DAY;
                break;
            case EVERY_WEEK:
                intervalUnit = DateBuilder.IntervalUnit.WEEK;
                break;
            case EVERY_MONTH:
                intervalUnit = DateBuilder.IntervalUnit.MONTH;
                break;
            case EVERY_YEAR:
                intervalUnit = DateBuilder.IntervalUnit.YEAR;
                break;
            default:
                throw new IllegalArgumentException("Unsupported TimeStatus: " + jobRequest.getStatus());
        }

             //building  the trigger time
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup() + "-trigger")
                .withDescription("Trigger for job: " + jobDetail.getKey().getName())
//                .startAt(startTime)
                .startNow()
                .withSchedule(
                        CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                                .withInterval(unitTime, intervalUnit)  // Use the calculated interval
                                .preserveHourOfDayAcrossDaylightSavings(true)  // Preserve the time across DST
                                .withMisfireHandlingInstructionFireAndProceed()  // Handle misfires by firing immediately
                )
                .build();
    }
}