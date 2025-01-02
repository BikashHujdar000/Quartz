package com.example.bikash.Optimized.Quartz.Models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.example.bikash.Optimized.Quartz.Enums.TimeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    @NotEmpty
    private String jobType;

    @NotEmpty
    private String jobGroup;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private ZoneId timeZone;

    @NotNull
    private Map<String, Object> jobData;

    @NotNull
    private  boolean recurring;

    private  int unitTime ;

    private TimeStatus status;

}
