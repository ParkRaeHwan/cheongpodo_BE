package com.example.cheongpodo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KakaoCoordsResponse {

    private List<Document> documents;

    @Data
    public static class Document {
        @JsonProperty("x")
        private String longitude; // 경도

        @JsonProperty("y")
        private String latitude; // 위도
    }
}
