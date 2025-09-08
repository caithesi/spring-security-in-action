package com.thesi.ssia.config;

import com.thesi.ssia.filter.DummyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebAuthorizationConfig {

    private final String authorizationKey;

    public WebAuthorizationConfig(@Value("${authorization.key}") String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//                .addFilterBefore(new DummyFilter("implicitly before http basic "), BasicAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new RequestIdValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new StaticKeyAuthenticationFilter(authorizationKey), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());
        //this may not work due to addFilter need class has order
        //however, this is a spring filter, not a spring security filter, so it doesnt have order
        //for now, this is what i know so far
//        httpSecurity.addFilter(new DummyFilter("implicitly before http basic "));
        return httpSecurity.build();
    }

}
