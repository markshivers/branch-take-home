package com.markshivers.branchtakehome.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.application.user-service")
public record UserServiceConfiguration(String url) {
}
