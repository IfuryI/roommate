package com.bh.roommate.application.impl.context;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.bh.roommate.application")
@EnableJpaRepositories("com.bh.roommate.application.api.repository")
@EntityScan("com.bh.roommate.application.api.model")
public class RoommateContext {

    public static void main(String[] args) {
        SpringApplication.run(RoommateContext.class, args);
    }
}
