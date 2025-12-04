package com.safemoney.safemoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.safemoney")
@EntityScan(basePackages = "com.safemoney.domains")
@EnableJpaRepositories(basePackages = "com.safemoney.repositories")
public class SafemoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafemoneyApplication.class, args);
    }
}
