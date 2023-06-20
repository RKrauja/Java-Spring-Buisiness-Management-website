package com.reiniskr.registrationloginspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/registration").permitAll()
                .requestMatchers("login").permitAll(); // Permit access to the registration page


//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login") // Specify the URL for the default login page
//                .permitAll();
        return http.build();

    }

}