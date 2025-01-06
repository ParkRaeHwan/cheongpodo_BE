package com.example.cheongpodo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "아아디는 필수임")
    private String username; // 아아디임

    @NotBlank(message = "비밀번호는 필수임")
    private String password;

}
