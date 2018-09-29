package com.training.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class LiquibaseConfig {

    @Autowired
    DataConfig dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/changelog-1.0.xml");
        liquibase.setDataSource(dataSource.dataSource());
        return liquibase;
    }
}
