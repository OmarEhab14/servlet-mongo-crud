package com.example.servletmongocrud.infrastructure.filters;

import com.example.servletmongocrud.application.exceptions.ApiException;
import com.example.servletmongocrud.application.exceptions.dtos.ExceptionResponse;
import com.example.servletmongocrud.application.mappers.ExceptionMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@WebFilter("/*")
public class GlobalExceptionFilter implements Filter {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ApiException e) {
            ExceptionResponse exceptionResponse = ExceptionMapper.toExceptionResponse(e);
            writeResponse(httpServletResponse, exceptionResponse);
        } catch (Exception e) {
            ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                    .status(500)
                    .title("Internal Server Error")
                    .detail(e.getMessage())
                    .timestamp(Instant.now())
                    .errors(List.of())
                    .build();

            writeResponse(httpServletResponse, exceptionResponse);
        }
    }

    private void writeResponse(HttpServletResponse httpServletResponse, ExceptionResponse exceptionResponse) throws IOException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(exceptionResponse.status());
        mapper.writeValue(httpServletResponse.getOutputStream(), exceptionResponse);
    }
}
