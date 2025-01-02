package com.example.bikash.Optimized.Configurations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class QuartDataSourceConfig {

    // Quartz DataSource for MSSQL
    @Bean(name = "quartzDataSource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url("jdbc:sqlserver://103.94.159.179:1433;databaseName=qt_db;encrypt=true;trustServerCertificate=true;")
                .username("sa")
                .password("_SBD@t@S0lution12!@")
                .build();
    }

    // Quartz JdbcTemplate
    @Bean(name = "quartzJdbcTemplate")
    public JdbcTemplate quartzJdbcTemplate(@Qualifier("quartzDataSource") DataSource quartzDataSource) {
        return new JdbcTemplate(quartzDataSource);
    }
}
