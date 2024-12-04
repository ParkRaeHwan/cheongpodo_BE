package com.example.cheongpodo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteSpaceResponse {
    private String message;
    private Long spaceId;
    private String status;
}
