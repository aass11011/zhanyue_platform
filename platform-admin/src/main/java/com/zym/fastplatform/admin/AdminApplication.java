package com.zym.fastplatform.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan(basePackages = {"com.zym.fastplatform"})
@EnableJpaRepositories(basePackages = {"com.zym.fastplatform"})
@SpringBootApplication(scanBasePackages = {"com.zym.fastplatform"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}