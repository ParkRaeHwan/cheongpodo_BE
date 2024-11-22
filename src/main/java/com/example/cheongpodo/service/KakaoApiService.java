package com.example.cheongpodo.service;

import com.example.cheongpodo.domain.AddressNotFoundException;
import com.example.cheongpodo.domain.KakaoCoordsResponse;
import com.example.cheongpodo.domain.KakaoFoodPlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final WebClient kakaoWebClient;

    // 해당 주소 경도, 위도 조회
    public KakaoCoordsResponse.Document getKakaoPosition(String address){
        KakaoCoordsResponse result = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/local/search/address.json")
                        .queryParam("query", address)
                        .build())
                .retrieve()
//                .onStatus(status -> {
//                    }, clientResponse -> {return Mono.error(new AddressNotFoundException());})
                .bodyToMono(KakaoCoordsResponse.class)
                .block();

        return result.getDocuments().stream()
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException());
    }

    // 해당 주소 기준 음식점 조회
    public List<KakaoFoodPlaceResponse.Document> getKakaoFoodPlace(String address){
        KakaoCoordsResponse.Document kakaoPosition = getKakaoPosition(address);
        String x = kakaoPosition.getLatitude();
        String y = kakaoPosition.getLongitude();

        KakaoFoodPlaceResponse response = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/local/search/keyword.json")
                        .queryParam("query", "음식점")
                        .queryParam("x", x)
                        .queryParam("y", y)
                        .queryParam("radius", 2000)
                        .queryParam("sort", "distance")
                        .build())
                .retrieve()
                .bodyToMono(KakaoFoodPlaceResponse.class)
                .block();

        return response != null ? response.getDocuments() : List.of();
    }

}
