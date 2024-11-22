package com.example.cheongpodo.controller;

import com.example.cheongpodo.domain.KakaoCoordsResponse;
import com.example.cheongpodo.domain.KakaoFoodPlaceResponse;
import com.example.cheongpodo.service.KakaoApiService;
import com.example.cheongpodo.service.OpenApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LocationController {

    private final KakaoApiService kakaoApiService;
    private final OpenApiService openApiService;


    @GetMapping("/search-position")
    public KakaoCoordsResponse.Document searchPosition(@RequestParam("address") String address) {
        return kakaoApiService.getKakaoPosition(address);
    }

    @GetMapping("/search-food")
    public List<KakaoFoodPlaceResponse.Document> searchFoodPlace(@RequestParam("address") String address) {
        return kakaoApiService.getKakaoFoodPlace(address);
    }

//    @GetMapping("/search-place")
//    public void searchPlace(@RequestParam("address") String address) {
//        openApiService.getOpenPlace(address);
//    }
}
