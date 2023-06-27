package com.reiniskr.registrationloginspring.config;

import com.reiniskr.registrationloginspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class SecurityConfiguration  {
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/registration").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("logout").permitAll()
                .requestMatchers("/dashboard").permitAll()
                .requestMatchers("/editProd").permitAll()
                .requestMatchers("/login").permitAll().and()
                .logout((logout) ->logout
                                .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();

    }

}