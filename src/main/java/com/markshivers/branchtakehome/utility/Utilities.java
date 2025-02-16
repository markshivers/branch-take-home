package com.markshivers.branchtakehome.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class Utilities {

    @Bean
    public RestTemplate RestTemplate(){
        return new RestTemplate();
    }

}
