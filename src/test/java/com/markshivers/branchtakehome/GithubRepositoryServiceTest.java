package com.markshivers.branchtakehome;

import static com.markshivers.branchtakehome.TestUtils.TEST_URL;
import static com.markshivers.branchtakehome.TestUtils.USERNAME;
import static com.markshivers.branchtakehome.TestUtils.objectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.ResponseEntity;

import com.markshivers.branchtakehome.config.RepositoryServiceConfiguration;
import com.markshivers.branchtakehome.github.GithubRepositoryService;
import com.markshivers.branchtakehome.utility.RestClient;
import com.markshivers.models.userRepository.GithubUserRepositoryResponse;

public class GithubRepositoryServiceTest {
    private static GithubRepositoryService githubRepositoryService;
    private static final RestClient restClient = mock();
    private final static String BAD_USERNAME_MESSAGE = "Unable to retrieve repositories for blank username";
    private static final RepositoryServiceConfiguration repositoryServiceConfiguration = new RepositoryServiceConfiguration(TEST_URL);

    @BeforeAll
    public static void setUp() {
        githubRepositoryService = new GithubRepositoryService(restClient, repositoryServiceConfiguration);
    }

    @Test
    public void retrieveRepositoriesHappyPath() throws IOException {
        InputStream expectedStream = this.getClass().getResourceAsStream("/GithubRepositoryResponse.json");
        GithubUserRepositoryResponse[] expectedResult = objectMapper.readValue(expectedStream, GithubUserRepositoryResponse[].class);
        ResponseEntity<GithubUserRepositoryResponse[]> expectedResponse = ResponseEntity.ok(expectedResult);
        when(restClient.getResponse(TEST_URL.formatted(USERNAME), GithubUserRepositoryResponse[].class)).thenReturn(expectedResponse);
        GithubUserRepositoryResponse[] actualResult = githubRepositoryService.getUserRepositories(USERNAME);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void retrieveRepositoriesErrorPath() {
        ResponseEntity<GithubUserRepositoryResponse[]> expectedResponse = ResponseEntity.badRequest().build();
        when(restClient.getResponse(TEST_URL.formatted(USERNAME), GithubUserRepositoryResponse[].class)).thenReturn(expectedResponse);
        assertThatThrownBy(() -> githubRepositoryService.getUserRepositories(USERNAME)).isInstanceOf(RuntimeException.class).hasMessage("Error retrieving user repositories");
    }


    @ParameterizedTest
    @MethodSource("com.markshivers.branchtakehome.TestUtils#badUsernames")
    public void retrieveRepositoriesBadUsername(String username) {
        assertThatThrownBy(() -> githubRepositoryService.getUserRepositories(username))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BAD_USERNAME_MESSAGE);
    }

}
