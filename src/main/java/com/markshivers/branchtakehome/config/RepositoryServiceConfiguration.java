package com.markshivers.branchtakehome.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.application.repository-service")
public record RepositoryServiceConfiguration(String url) {
}
