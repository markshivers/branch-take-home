package com.markshivers.branchtakehome.utility;

import org.springframework.http.HttpHeaders;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HeaderProvider {

    public HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
