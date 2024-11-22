package com.example.cheongpodo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoFoodPlaceResponse {
    List<Document> documents;

    @Data
    public static class Document {
        @JsonProperty("place_name")
        private String place_name;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("place_url")
        private String place_url;

        @JsonProperty("x")
        private String longitude; // 경도

        @JsonProperty("y")
        private String latitude; // 위도
    }

}
