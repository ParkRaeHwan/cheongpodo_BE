package com.example.cheongpodo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${kakao.secret-key}")
    private String kakoSecretKey;

    @Bean(name = "kakaoWebClient")
    WebClient kakoWebClient() {
        return WebClient.builder()
                .baseUrl("http://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakoSecretKey)
                .build();
    }
}
