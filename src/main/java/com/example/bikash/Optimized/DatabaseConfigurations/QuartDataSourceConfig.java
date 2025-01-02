package com.example.bikash.Optimized.DatabaseConfigurations;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartDataSourceConfig {


    @Value("${quartz.datasource.driver}")
    private String driverClassName;

    @Value("${quartz.datasource.url}")
    private String jdbcUrl;

    @Value("${quartz.datasource.username}")
    private String username;

    @Value("${quartz.datasource.password}")
    private String password;

    @Value("${quartz.datasource.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${quartz.datasource.minimumIdle}")
    private int minimumIdle;

    @Value("${quartz.datasource.idleTimeout}")
    private long idleTimeout;

    @Value("${quartz.datasource.connectionTimeout}")
    private long connectionTimeout;

    @Value("${quartz.datasource.poolName}")
    private String poolName;

    @Value("${quartz.datasource.provider}")
    private String providerConnection;

    @Value("${quartz.jobstore.class}")
    private String jobStoreClass;

    @Value("${quartz.jobstore.driverDelegateClass}")
    private String driverDelegateClass;

    @Value("${quartz.jobstore.dataSource}")
    private String jobStoreDataSource;

    @Value("${quartz.scheduler.instanceName}")
    private String schedulerInstanceName;

    @Value("${quartz.scheduler.instanceId}")
    private String schedulerInstanceId;

    @Value("${quartz.jobStore.clusterCheckinInterval}")
    private long clusterCheckinInterval;

    @Value("${quartz.jobStore.isClustered}")
    private boolean isClustered;

    @Value("${quartz.jobStore.useProperties}")
    private boolean useProperties;

    // Quartz DataSource for MSSQL
    @Bean(name = "quartzDataSource")
    public DataSource quartzDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setPoolName(poolName);
        hikariConfig.setDataSourceProperties(getProperties());
        return new HikariDataSource(hikariConfig);
    }



    public   Properties getProperties()
    {

        Properties quartzProperties = new Properties();
        quartzProperties.put("org.quartz.scheduler.instanceName",schedulerInstanceName);
        quartzProperties.put("org.quartz.scheduler.instanceId",schedulerInstanceId);
        quartzProperties.put("org.quartz.jobStore.class",jobStoreClass);
        quartzProperties.put("org.quartz.jobStore.driverDelegateClass",driverDelegateClass);
        quartzProperties.put("org.quartz.jobStore.dataSource",jobStoreDataSource);
        quartzProperties.put("org.quartz.dataSource.quartzDataSource.provider",providerConnection); // Use HikariCP
        quartzProperties.put("org.quartz.dataSource.quartzDataSource.driver",driverClassName);
        quartzProperties.put("org.quartz.dataSource.quartzDataSource.URL",jdbcUrl);
        quartzProperties.put("org.quartz.dataSource.quartzDataSource.user",username);
        quartzProperties.put("org.quartz.dataSource.quartzDataSource.password",password);
        quartzProperties.put("org.quartz.jobStore.isClustered",isClustered); // Enable clustering
        quartzProperties.put("org.quartz.jobStore.clusterCheckinInterval","20000");
        quartzProperties.put("org.quartz.jobStore.useProperties",useProperties);
        // Check-in interval (20 seconds)


        return  quartzProperties;
    }


    // Quartz JdbcTemplate
    @Bean(name = "quartzJdbcTemplate")
    public JdbcTemplate quartzJdbcTemplate(@Qualifier("quartzDataSource") DataSource quartzDataSource) {
        return new JdbcTemplate(quartzDataSource);
    }
}



// IN the properties section you can add following properties  for hickari pool Connections

//    quartzProperties.put("org.quartz.dataSource.quartzDataSource.maxConnections", maximumPoolSize);
//        quartzProperties.put("org.quartz.dataSource.quartzDataSource.idleTimeout", idleTimeout);
//        quartzProperties.put("org.quartz.dataSource.quartzDataSource.connectionTimeout", connectionTimeout);
//        quartzProperties.put("org.quartz.dataSource.quartzDataSource.minimumIdle", minimumIdle);
//        quartzProperties.put("org.quartz.dataSource.quartzDataSource.maximumPoolSize", maximumPoolSize);
