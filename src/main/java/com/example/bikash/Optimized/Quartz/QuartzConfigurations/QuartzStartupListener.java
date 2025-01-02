package com.example.bikash.Optimized.Quartz.QuartzConfigurations;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class QuartzStartupListener {

    private final Scheduler scheduler;

    @Autowired
    public QuartzStartupListener(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startQuartzScheduler() {
        try {
            scheduler.start();
            System.out.println("Quartz Scheduler started after schema initialization.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
