package com.example.demowebmvc.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.remoting.RemoteAccessException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
@RequiredArgsConstructor
public class RemoteApiCall {
    private final OkHttpClient okHttpClient;

    private final ObjectMapper mapper;

    public JsonNode get(String url) {
        Request request = new Request.Builder()
        .url(url)
        .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            String resBody = response.body().string();
            return mapper.readTree(resBody);

        } catch (Exception e) {
            throw new RemoteAccessException("remote error", e);
        }
    }


}
