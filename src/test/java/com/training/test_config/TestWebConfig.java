package com.training.test_config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan({
        "com.training.repositories", "com.training.entities", "com.training.listeners", "com.training.test_config"
})
public class TestWebConfig {
}
