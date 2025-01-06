package com.example.cheongpodo.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private Long youthSpaceId;
    private String username;
    private String nickname;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
