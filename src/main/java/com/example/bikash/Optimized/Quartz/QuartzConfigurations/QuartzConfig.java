package com.example.bikash.Optimized.Quartz.QuartzConfigurations;

import com.example.bikash.Optimized.DatabaseConfigurations.QuartDataSourceConfig;
import org.quartz.Scheduler;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import javax.sql.DataSource;
@Configuration
public class QuartzConfig {

    @Autowired
    private  ApplicationContext applicationContext;


    @Autowired
    private QuartDataSourceConfig  dataSourceConfig;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("quartzDataSource") DataSource quartzDataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(dataSourceConfig.getProperties());
        factory.setJobFactory(jobFactory());
        factory.setSchedulerName("QuartzScheduler");
        factory.setAutoStartup(false);
        return factory;
    }


    @Bean
    public JobFactory jobFactory() {
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean factory) {
        return factory.getScheduler();
    }

}


