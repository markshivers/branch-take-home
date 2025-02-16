package com.markshivers.branchtakehome.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.markshivers.branchtakehome.github.GithubDataService;
import com.markshivers.branchtakehome.model.UserView;
import com.markshivers.models.user.GithubUserResponse;
import com.markshivers.models.userRepository.GithubUserRepositoryResponse;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VcsRestController {
    private GithubDataService githubDataService;

    public VcsRestController(GithubDataService githubDataService) {
        this.githubDataService = githubDataService;
    }

    @GetMapping(value = "/compositeUserInformation/{username}")
    public ResponseEntity<UserView> getUserView(@PathVariable String username) {
        return ResponseEntity.ok(githubDataService.getGithubUserView(username));
    }
}
