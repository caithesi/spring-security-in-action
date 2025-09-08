package com.thesi.ssia.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;


public class RequestIdValidationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestIdValidationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        String requestId = httpRequest.getHeader("Request-Id");
        if (!StringUtils.hasLength(requestId)) {
            logger.error("lack of request id");
            var httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
