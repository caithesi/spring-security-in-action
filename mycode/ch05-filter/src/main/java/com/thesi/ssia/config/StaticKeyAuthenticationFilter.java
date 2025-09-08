package com.thesi.ssia.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

public class StaticKeyAuthenticationFilter implements Filter {

    private final String authorizationKey;

    public StaticKeyAuthenticationFilter(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var httpResponse = (HttpServletResponse) servletResponse;
        String authentication = httpRequest.getHeader("Authorization");
        if (Objects.equals(authentication, authorizationKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
