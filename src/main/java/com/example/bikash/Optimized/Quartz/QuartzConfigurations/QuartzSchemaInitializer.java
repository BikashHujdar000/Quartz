package com.example.bikash.Optimized.Quartz.QuartzConfigurations;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class QuartzSchemaInitializer {

    private final JdbcTemplate quartzJdbcTemplate;

    public QuartzSchemaInitializer(@Qualifier("quartzJdbcTemplate") JdbcTemplate quartzJdbcTemplate) {
        this.quartzJdbcTemplate = quartzJdbcTemplate;
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing Quartz schema...");
        try {
            String script = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("script.sql").toURI())));
            String[] sqlStatements = script.split(";");

            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    quartzJdbcTemplate.execute(sql);
                }
            }
            System.out.println("Quartz schema initialized successfully.");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error initializing Quartz schema", e);
        }
    }
}
