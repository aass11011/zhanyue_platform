package com.zym.fastplatform.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.zym.fastplatform"})
@SpringBootApplication(scanBasePackages = {"com.zym.fastplatform.portal","com.zym.fastplatform.stock"},exclude = {SecurityAutoConfiguration.class})
@EntityScan(basePackages = {"com.zym.fastplatform"})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
