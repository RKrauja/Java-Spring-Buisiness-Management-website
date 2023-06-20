package com.reiniskr.registrationloginspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
class SecurityConfiguration {

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/registration").permitAll(); // Permit access to the registration page
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") // Specify the URL for the default login page
//                .permitAll();
        return http.build();
    }

}