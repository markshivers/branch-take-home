package com.markshivers.branchtakehome;

import static com.markshivers.branchtakehome.TestUtils.TEST_URL;
import static com.markshivers.branchtakehome.TestUtils.USERNAME;
import static com.markshivers.branchtakehome.TestUtils.objectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.markshivers.branchtakehome.github.GithubDataService;
import com.markshivers.branchtakehome.model.UserView;
import com.markshivers.branchtakehome.utility.RestClient;
import com.markshivers.models.user.GithubUserResponse;
import com.markshivers.models.userRepository.GithubUserRepositoryResponse;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GithubDataServiceTest {

    @Autowired
    private GithubDataService githubDataService;

    private static final String USER_URL = "https://api.github.com/users/octocat";
    private static final String REPO_URL = "https://api.github.com/users/octocat/repos";

    @MockitoBean
    private final RestClient restClient = mock();

    @BeforeEach
    public void setUp() throws IOException {
        InputStream expectedUserStream = GithubDataServiceTest.class.getResourceAsStream("/GithubUserResponse.json");
        GithubUserResponse expectedUserResult = objectMapper.readValue(expectedUserStream, GithubUserResponse.class);
        ResponseEntity<GithubUserResponse> expectedUserResponse = ResponseEntity.ok(expectedUserResult);
        when(restClient.getResponse(USER_URL, GithubUserResponse.class)).thenReturn(expectedUserResponse);

        InputStream expectedRepoStream = GithubDataServiceTest.class.getResourceAsStream("/GithubRepositoryResponse.json");
        GithubUserRepositoryResponse[] expectedRepoResult = objectMapper.readValue(expectedRepoStream, GithubUserRepositoryResponse[].class);
        ResponseEntity<GithubUserRepositoryResponse[]> expectedRepoResponse = ResponseEntity.ok(expectedRepoResult);
        when(restClient.getResponse(REPO_URL, GithubUserRepositoryResponse[].class)).thenReturn(expectedRepoResponse);
    }

    @Test
    public void getUserViewTest() throws IOException {
        InputStream expectedUserViewStream = GithubDataServiceTest.class.getResourceAsStream("/UserView.json");
        UserView expectedUserViewResult = objectMapper.readValue(expectedUserViewStream, UserView.class);
        UserView actualResult = githubDataService.getGithubUserView("octocat");

        assertThat(actualResult).isEqualTo(expectedUserViewResult);
    }
}
