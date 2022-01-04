package com.example.demowebmvc.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;

@Configuration
public class OkhttpConfig {
    
    @Bean
    public OkHttpClient okHttpClient() {
         return new OkHttpClient.Builder()
         .callTimeout(Duration.ofSeconds(5))
         .build();
      
    }
}
