package com.markshivers.branchtakehome.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.markshivers.branchtakehome.config.UserServiceConfiguration;

@Service
public class GithubUserService {
    private RestTemplate restTemplate;
    private UserServiceConfiguration userServiceConfiguration;

    @Autowired
    public GithubUserService(RestTemplate restTemplate,
                             UserServiceConfiguration userServiceConfiguration){
        this.restTemplate = restTemplate;
        this.userServiceConfiguration = userServiceConfiguration;
    }

//    public getVcsUser(String username) {
//
//    }
}
