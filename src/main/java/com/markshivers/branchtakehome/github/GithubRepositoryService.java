package com.markshivers.branchtakehome.github;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.markshivers.branchtakehome.config.RepositoryServiceConfiguration;
import com.markshivers.branchtakehome.utility.RestClient;
import com.markshivers.models.userRepository.GithubUserRepositoryResponse;

@Service
public class GithubRepositoryService {
    private final RestClient restClient;
    private final RepositoryServiceConfiguration repositoryServiceConfiguration;

    public GithubRepositoryService(RestClient restClient, RepositoryServiceConfiguration repositoryServiceConfiguration) {

        this.restClient = restClient;
        this.repositoryServiceConfiguration = repositoryServiceConfiguration;
    }

    public GithubUserRepositoryResponse[] getUserRepositories(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Unable to retrieve repositories for blank username");
        }

        ResponseEntity<GithubUserRepositoryResponse[]> callResponse =
                restClient.getResponse(repositoryServiceConfiguration.url().formatted(username), GithubUserRepositoryResponse[].class);
        if (callResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Error retrieving user repositories");
        }

        return callResponse.getBody();
    }
}
