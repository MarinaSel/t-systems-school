package com.training.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySources({ @PropertySource("classpath:properties/liquibase.properties") })
public class LiquibaseConfig {

    @Autowired
    DataConfig dataSource;
    @Autowired
    private Environment env;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(env.getProperty("changeLogFile"));
        liquibase.setDataSource(dataSource.dataSource());
        return liquibase;
    }
}
