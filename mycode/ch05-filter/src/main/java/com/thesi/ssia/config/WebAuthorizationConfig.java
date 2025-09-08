package com.thesi.ssia.config;

import com.thesi.ssia.filter.DummyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebAuthorizationConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .addFilterBefore(new DummyFilter("implicitly before http basic "), BasicAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());
        //this may not work due to addFilter need class has order
        //however, this is a spring filter, not a spring security filter, so it doesnt have order
        //for now, this is what i know so far
//        httpSecurity.addFilter(new DummyFilter("implicitly before http basic "));
        return httpSecurity.build();
    }

}
