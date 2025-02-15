package com.markshivers.branchtakehome.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.application.user-service")
public class UserServiceConfiguration {
    public String url;
}
