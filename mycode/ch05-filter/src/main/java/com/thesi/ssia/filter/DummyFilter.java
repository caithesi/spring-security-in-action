package com.thesi.ssia.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * this filter doesnt do much, it just for proof of concept
 */
public class DummyFilter extends OncePerRequestFilter {

    private final String additionalText;



    // Create a logger for this class
    private static final Logger logger =  LoggerFactory.getLogger(DummyFilter.class);

    public DummyFilter(String additionalText) {
        this.additionalText = additionalText;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("dummy filter was called {}", additionalText);
        filterChain.doFilter(request, response);
    }
}
