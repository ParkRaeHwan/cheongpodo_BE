package com.example.cheongpodo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${kakao.secret-key}")
    private String kakoSecretKey;
    @Value("${openApi.secret-key}")
    private String openApiSecretKey;

    @Bean(name = "kakaoWebClient")
    WebClient kakoWebClient() {
        return WebClient.builder()
                .baseUrl("http://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK " + kakoSecretKey)
                .build();
    }

    @Bean(name = "openWebClient")
    WebClient openWebClient() {
        return WebClient.builder()
                .baseUrl("https://www.youthcenter.go.kr/opi/wantedSpace.do")
                .defaultHeader("openApiVlak", openApiSecretKey)
                .build();
    }

}
