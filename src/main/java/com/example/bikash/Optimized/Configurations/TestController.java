package com.example.bikash.Optimized.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate primaryJdbcTemplate; // Primary JdbcTemplate

    @Autowired
    private JdbcTemplate quartzJdbcTemplate; // Quartz JdbcTemplate

    @GetMapping("/test-primary-db")
    public String testPrimaryDb() {
        return primaryJdbcTemplate.queryForObject("SELECT 1", Integer.class).toString();
    }

    @GetMapping("/test-quartz-db")
    public String testQuartzDb() {
        return quartzJdbcTemplate.queryForObject("SELECT 1", Integer.class).toString();
    }
}
