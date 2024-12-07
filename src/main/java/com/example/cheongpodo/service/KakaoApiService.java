package com.example.cheongpodo.service;

import com.example.cheongpodo.exception.AddressNotFoundException;
import com.example.cheongpodo.response.KakaoCoordsResponse;
import com.example.cheongpodo.response.KakaoFoodPlaceResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    private final WebClient.Builder webClientBuilder;
    private WebClient kakaoWebClient;

    @PostConstruct
    public void init() {
        this.kakaoWebClient = webClientBuilder
                .baseUrl("https://dapi.kakao.com")
                .defaultHeader("Authorization", "KakaoAK b0ec95afbb30c9bb234e6c16f66ec9a4")
                .build();
    }

    // 해당 주소 경도, 위도 조회
    public KakaoCoordsResponse.Document getKakaoPosition(String address) {
        KakaoCoordsResponse result = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/local/search/address.json")
                        .queryParam("query", address)
                        .build())
                .retrieve()
                .bodyToMono(KakaoCoordsResponse.class)
                .block();

        return result.getDocuments().stream()
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException());
    }

    // 해당 주소 기준 음식점 조회
    public List<KakaoFoodPlaceResponse.Document> getKakaoFoodPlace(String address, String categoryCode) {
        // 카카오 좌표 정보 가져오기
        KakaoCoordsResponse.Document kakaoPosition = getKakaoPosition(address);
        String x = kakaoPosition.getLongitude();  // 경도
        String y = kakaoPosition.getLatitude();   // 위도

        List<KakaoFoodPlaceResponse.Document> allDocuments = new ArrayList<>();
        int page = 1;

        while (true) {
            // 카카오 로컬 API 호출
            int finalPage = page;

            KakaoFoodPlaceResponse response = kakaoWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/v2/local/search/category.json")
                            .queryParam("category_group_code", categoryCode)
                            .queryParam("x", x)
                            .queryParam("y", y)
                            .queryParam("radius", 400)
                            .queryParam("page", finalPage)
                            .queryParam("sort", "distance")
                            .build())
                    .retrieve()
                    .bodyToMono(KakaoFoodPlaceResponse.class)
                    .block();

            allDocuments.addAll(response.getDocuments());
            if (response.getMeta().isEnd()) {
                break;
            }
            page++;
        }

        return allDocuments;
    }

}
