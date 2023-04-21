package com.example.computershop.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
        "com.example.computershop.Repositories"
})
@Configuration
@ComponentScan
public class DbConfig {
}
