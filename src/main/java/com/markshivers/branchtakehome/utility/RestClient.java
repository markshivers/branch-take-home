package com.markshivers.branchtakehome.utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {
    private HeaderProvider headerProvider;
    private RestTemplate restTemplate;

    public RestClient(HeaderProvider headerProvider, RestTemplate restTemplate){
        this.headerProvider = headerProvider;
        this.restTemplate = restTemplate;
    }

    /**
     * Use the RestTemplate to GET an object from a given URL to the request return type
     */
    public <T> T get(String url, Class<T> returnType) {
        HttpEntity<HttpStatus> entity = new HttpEntity<>(headerProvider.buildHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, returnType).getBody();
    }

    public <T> ResponseEntity<T> getResponse(String url, Class<T> returnType){
        HttpEntity<HttpStatus> entity = new HttpEntity<>(headerProvider.buildHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, returnType);
    }
}
