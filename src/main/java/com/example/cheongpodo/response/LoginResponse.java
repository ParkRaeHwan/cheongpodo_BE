package com.example.cheongpodo.response;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token; // 생성된 token 저장
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private List<Long> favoriteSpaces;
}
