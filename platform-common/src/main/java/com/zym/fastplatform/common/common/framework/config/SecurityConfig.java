package com.zym.fastplatform.common.common.framework.config;

import com.zym.fastplatform.common.common.framework.repository.JpaUserDetailsService;
import com.zym.fastplatform.common.common.framework.security.ExceptionHandlerFilter;
import com.zym.fastplatform.common.common.framework.security.JwtAuthenticationTokenFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
public class SecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;
    @Resource
    private JpaUserDetailsService jpaUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(
                        (authz) -> authz
                            .requestMatchers("/index/login","/index/getCode","/index/registe").permitAll()
                            .requestMatchers("/api/v1/index/login","/api/v1/index/getCode","/api/v1/index/registe").permitAll()
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**","/swagger-ui.html").permitAll()
                            .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults()).cors().and().userDetailsService(jpaUserDetailsService);
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        ((UrlBasedCorsConfigurationSource)source).registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

}
