package com.example.cheongpodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final WebClient openWebClient;

//    public OpenPlaceResponse getOpenPlace(String address) {
//        openWebClient.get()
//    }

}
