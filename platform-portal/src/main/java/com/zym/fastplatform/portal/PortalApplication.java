package com.zym.fastplatform.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.zym.fastplatform"})
@SpringBootApplication(scanBasePackages = {"com.zym.fastplatform.portal","com.zym.fastplatform.common"},exclude = {
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
    org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
@ComponentScan(
    basePackages = {"com.zym.fastplatform.portal","com.zym.fastplatform.common"},
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {
            com.zym.fastplatform.common.common.framework.config.SecurityConfig.class,
            com.zym.fastplatform.common.system.service.impl.SysUserServiceImpl.class,
            com.zym.fastplatform.common.common.framework.security.JwtAuthenticationTokenFilter.class,
            com.zym.fastplatform.common.common.framework.security.ExceptionHandlerFilter.class,
            com.zym.fastplatform.common.common.framework.config.SecurityIgnoreUrl.class
        }
    )
)
@EntityScan(basePackages = {"com.zym.fastplatform"})
public class PortalApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}