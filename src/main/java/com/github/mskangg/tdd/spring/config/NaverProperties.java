package com.github.mskangg.tdd.spring.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "naver.openapi")
public class NaverProperties {

    private final String movieUrl;
    private final String clientId;
    private final String clientSecret;
}
