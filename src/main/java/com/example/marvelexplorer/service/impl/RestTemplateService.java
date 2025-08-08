package com.example.marvelexplorer.service.impl;

import com.example.marvelexplorer.exception.ApiErrorException;
import com.example.marvelexplorer.service.httpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class RestTemplateService implements httpClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T doGet(String endpoing, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endpoing, queryParams);

        HttpEntity<Void> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Endpoing error [ %s - %s], response code: %s", HttpMethod.GET, endpoing, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    private String buildFinalUrl(String endpoing, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoing);

        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        return builder.build().toUriString();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.put("Authorization", Collections.singletonList("bearer jwt"));
        return headers;
    }

    @Override
    public <T, R> T doPost(String endpoing, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endpoing, queryParams);

        HttpEntity<R> httpEntity = new HttpEntity<>(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value() || response.getStatusCode().value() != HttpStatus.CREATED.value()) {
            String message = String.format("Endpoing error [ %s - %s], response code: %s", HttpMethod.POST, endpoing, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endpoing, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endpoing, queryParams);

        HttpEntity<R> httpEntity = new HttpEntity<>(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Endpoing error [ %s - %s], response code: %s", HttpMethod.PUT, endpoing, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endpoing, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endpoing, queryParams);

        HttpEntity<Void> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Endpoing error [ %s - %s], response code: %s", HttpMethod.DELETE, endpoing, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }
}
