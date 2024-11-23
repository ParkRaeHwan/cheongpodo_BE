package com.example.cheongpodo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoFoodPlaceResponse {
    List<Document> documents;
    private Meta meta;

    @Data
    public static class Meta {
        @JsonProperty("total_count")
        public int totalCount;

        @JsonProperty("pageable_count")
        public int pageableCount;

        @JsonProperty("is_end")
        public boolean isEnd;
    }

    @Data
    public static class Document {
        @JsonProperty("place_name")
        private String place_name;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("place_url")
        private String place_url;

        @JsonProperty("distance")
        private String distance;

        @JsonProperty("x")
        private String longitude; // 경도

        @JsonProperty("y")
        private String latitude; // 위도
    }

}
