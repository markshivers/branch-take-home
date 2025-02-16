package com.markshivers.branchtakehome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ConfigurationPropertiesScan
public class BranchtakehomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchtakehomeApplication.class, args);
	}

}
