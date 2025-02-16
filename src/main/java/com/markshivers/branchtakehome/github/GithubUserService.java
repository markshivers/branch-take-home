package com.markshivers.branchtakehome.github;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.markshivers.branchtakehome.utility.RestClient;
import com.markshivers.models.user.GithubUserResponse;

import com.markshivers.branchtakehome.config.UserServiceConfiguration;

@Service
public class GithubUserService {
    private final UserServiceConfiguration userServiceConfiguration;
    private final RestClient restClient;

    public GithubUserService(RestClient restClient,
                             UserServiceConfiguration userServiceConfiguration) {
        this.restClient = restClient;
        this.userServiceConfiguration = userServiceConfiguration;
    }

    public GithubUserResponse getUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Unable to retrieve user with blank username");
        }
        ResponseEntity<GithubUserResponse> callResponse = restClient.getResponse(userServiceConfiguration.url().formatted(username), GithubUserResponse.class);
        if (callResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Something went wrong calling github get user");
        }
            return callResponse.getBody();

    }
}
