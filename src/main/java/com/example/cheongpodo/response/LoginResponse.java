package com.example.cheongpodo.response;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token; // 생성된 token 저장
}
