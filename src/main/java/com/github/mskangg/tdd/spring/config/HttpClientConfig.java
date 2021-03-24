package com.github.mskangg.tdd.spring.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "http.client")
public class HttpClientConfig {

    private final int maxConnTotal;
    private final int maxConnPerRoute;
    private final int connectTimeout;
    private final int readTimeout;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(maxConnTotal)
                .setMaxConnPerRoute(maxConnPerRoute)
                .build();

        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return new RestTemplate(factory);
    }
}
