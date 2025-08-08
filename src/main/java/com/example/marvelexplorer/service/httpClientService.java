package com.example.marvelexplorer.service;

import java.util.Map;

public interface httpClientService {

    <T> T doGet(String endpoing, Map<String, String> queryParams, Class<T> responseType);

    <T, R> T doPost(String endpoing, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);

    <T, R> T doPut(String endpoing, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);

    <T> T doDelete(String endpoing, Map<String, String> queryParams, Class<T> responseType);

}
