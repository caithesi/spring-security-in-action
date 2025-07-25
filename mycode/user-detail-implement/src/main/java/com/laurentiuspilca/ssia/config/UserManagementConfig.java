package com.laurentiuspilca.ssia.config;

import com.laurentiuspilca.ssia.model.User;
import com.laurentiuspilca.ssia.services.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

/**
 * this class was designed with two responsibilities for user management: UserDetailsService and PasswordEncoder
 */
@Configuration
public class UserManagementConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails u = new User("john", "12345", "read");
//        List<UserDetails> users = List.of(u);
//        return new InMemoryUserDetailsService(users);
//    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
            return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }
}
