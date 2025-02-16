package com.markshivers.branchtakehome.github;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.markshivers.branchtakehome.model.RepositoryView;
import com.markshivers.branchtakehome.model.UserView;
import com.markshivers.models.user.GithubUserResponse;
import com.markshivers.models.userRepository.GithubUserRepositoryResponse;

@Service
public class GithubDataService {
    private GithubUserService githubUserService;
    private GithubRepositoryService githubRepositoryService;

    public GithubDataService(GithubUserService githubUserService, GithubRepositoryService githubRepositoryService) {
        this.githubUserService = githubUserService;

        this.githubRepositoryService = githubRepositoryService;
    }

    public UserView getGithubUserView(String username) {
        GithubUserRepositoryResponse[] repositories = githubRepositoryService.getUserRepositories(username);
        GithubUserResponse userResponse = githubUserService.getUser(username);

        return mapToUserView(userResponse, repositories);
    }

    private UserView mapToUserView(GithubUserResponse userResponse, GithubUserRepositoryResponse[] repositories) {
        return new UserView(
                userResponse.getLogin(),
                userResponse.getName(),
                Optional.ofNullable(userResponse.getAvatarUrl()).map(Object::toString).orElse(null),
                getAdditionalProperty("location", userResponse),
                userResponse.getEmail(),
                Optional.ofNullable(userResponse.getUrl()).map(Object::toString).orElse(null),
                getAdditionalProperty("created_at", userResponse),
                Arrays.stream(repositories).map(this::mapToRepositoryView).collect(Collectors.toList()));
    }

    private RepositoryView mapToRepositoryView(GithubUserRepositoryResponse repository) {
        return new RepositoryView(repository.getName(), repository.getHtmlUrl().toString());
    }

    private String getAdditionalProperty(String key, GithubUserResponse userResponse) {
        Optional<Object> optProperty = Optional.ofNullable(userResponse.getAdditionalProperties().get(key));
        return optProperty.map(Object::toString).orElse(null);
    }
}
