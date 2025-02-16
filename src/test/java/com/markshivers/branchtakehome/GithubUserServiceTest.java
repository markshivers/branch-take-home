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

import com.markshivers.branchtakehome.config.UserServiceConfiguration;
import com.markshivers.branchtakehome.github.GithubUserService;
import com.markshivers.branchtakehome.utility.RestClient;
import com.markshivers.models.user.GithubUserResponse;

public class GithubUserServiceTest {
    private static GithubUserService githubUserService;
    private static final RestClient restClient = mock();
    private static final UserServiceConfiguration userServiceConfiguration = new UserServiceConfiguration(TEST_URL);

    @BeforeAll
    public static void setUp() {
        githubUserService = new GithubUserService(restClient, userServiceConfiguration);
    }

    @Test
    public void retrieveUserHappyPath() throws IOException {
        InputStream expectedStream = this.getClass().getResourceAsStream("/GithubUserResponse.json");
        GithubUserResponse expectedResult = objectMapper.readValue(expectedStream, GithubUserResponse.class);
        ResponseEntity<GithubUserResponse> expectedResponse = ResponseEntity.ok(expectedResult);
        when(restClient.getResponse(TEST_URL.formatted(USERNAME), GithubUserResponse.class)).thenReturn(expectedResponse);
        GithubUserResponse actualResult = githubUserService.getUser(USERNAME);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void retrieveUserErrorPath() {
        ResponseEntity<GithubUserResponse> expectedResponse = ResponseEntity.badRequest().build();
        when(restClient.getResponse(TEST_URL.formatted(USERNAME), GithubUserResponse.class)).thenReturn(expectedResponse);
        assertThatThrownBy(() -> githubUserService.getUser(USERNAME)).isInstanceOf(RuntimeException.class).hasMessage("Something went wrong calling github get user");
    }

    @ParameterizedTest
    @MethodSource("com.markshivers.branchtakehome.TestUtils#badUsernames")
    public void retrieveRepositoriesBadUsername(String username) {
        assertThatThrownBy(() -> githubUserService.getUser(username))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unable to retrieve user with blank username");
    }

}
