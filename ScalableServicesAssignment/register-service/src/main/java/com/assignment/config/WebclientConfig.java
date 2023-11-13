package com.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    private final String dispatchServiceURL =  "http://dispatch-service:8082";
    private final String emilServiceURL = "http://email-service:8083";

    @Bean(name = "dispatch")
    @Primary
    public WebClient dispatchWebClient() {
        return WebClient.builder().baseUrl(dispatchServiceURL).build();
    }

    @Bean(name = "email")
    @Primary
    public WebClient emailWebClient() {
        return WebClient.builder().baseUrl(emilServiceURL).build();
    }
}
