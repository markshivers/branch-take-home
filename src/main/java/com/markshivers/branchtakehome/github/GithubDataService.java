package com.markshivers.branchtakehome.github;

import java.util.Arrays;
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

    public GithubDataService(GithubUserService githubUserService, GithubRepositoryService githubRepositoryService){
        this.githubUserService = githubUserService;

        this.githubRepositoryService = githubRepositoryService;
    }

    public GithubUserResponse getGithubUser(String username) {
        return githubUserService.getVcsUser(username);
    }

    public GithubUserRepositoryResponse[] getGithubUserRepositories(String username) {
        return githubRepositoryService.getUserRepositories(username);
    }

    public UserView getGithubUserView(String username) {
        GithubUserRepositoryResponse[] repositories = githubRepositoryService.getUserRepositories(username);
        GithubUserResponse userResponse = githubUserService.getVcsUser(username);

        return mapToUserView(userResponse, repositories);
    }

    private UserView mapToUserView(GithubUserResponse userResponse, GithubUserRepositoryResponse[] repositories){
        return new UserView(
                userResponse.getLogin(),
                userResponse.getName(),
                userResponse.getAvatarUrl().toString(),
                userResponse.getAdditionalProperties().get("location").toString(),
                userResponse.getEmail(),
                userResponse.getUrl().toString(),
                userResponse.getAdditionalProperties().get("created_at").toString(),
                Arrays.stream(repositories).map(this::mapToRepositoryView).collect(Collectors.toList()));
    }

    private RepositoryView mapToRepositoryView(GithubUserRepositoryResponse repository) {
        return new RepositoryView(repository.getName(), repository.getHtmlUrl().toString());
    }
}
