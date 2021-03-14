package com.example.bean;

import com.google.gson.Gson;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }
}
