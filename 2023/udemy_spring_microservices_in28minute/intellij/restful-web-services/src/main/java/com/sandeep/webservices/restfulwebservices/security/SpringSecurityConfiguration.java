package com.sandeep.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //All requests are authenticated
        http.authorizeHttpRequests(
                auth-> auth.anyRequest().authenticated()
        );

        //basic authentication window
        http.httpBasic(Customizer.withDefaults());

        //CSRF disable to allow post request
        http.csrf().disable();

        return http.build();
    }
}
