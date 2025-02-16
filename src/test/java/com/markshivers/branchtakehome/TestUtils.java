package com.markshivers.branchtakehome;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public final static String TEST_URL = "testUrl/%s";
    public final static String USERNAME = "octocat";
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String[] badUsernames() {
        return new String[]{ "", " ", null };
    }
}
