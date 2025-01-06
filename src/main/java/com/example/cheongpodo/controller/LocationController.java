package com.example.cheongpodo.controller;

import com.example.cheongpodo.domain.KakaoCoordsResponse;
import com.example.cheongpodo.domain.KakaoFoodPlaceResponse;
import com.example.cheongpodo.service.KakaoApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LocationController {

    private final KakaoApiService kakaoApiService;

    @Operation(summary = "입력한 주소의 위도, 경도값 조회 API",
                description = "클라이언트가 요청한 주소값에 대한 위도, 경도 API")
    @Parameter(name = "address", description = "클라이언트가 요청한 주소 입력")
    @GetMapping("/search-position")
    public KakaoCoordsResponse.Document searchPosition(@RequestParam("address") String address) {
        return kakaoApiService.getKakaoPosition(address);
    }

    @Operation(summary = "입력한 주소 근처 음식점 조회 API",
            description = "클라이언트가 요청한 주소값 반경 2km내 음식점을 조회해주는 API")
    @Parameter(name = "address", description = "클라이언트가 요청한 주소 입력")
    @GetMapping("/search-food")
    public List<KakaoFoodPlaceResponse.Document> searchFoodPlace(@RequestParam("address") String address) {
        return kakaoApiService.getKakaoFoodPlace(address);
    }

}
