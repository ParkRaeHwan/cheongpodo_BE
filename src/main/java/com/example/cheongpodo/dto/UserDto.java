package com.example.cheongpodo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    @NotBlank(message = "아이디는 필수 항목 입니다.")
    private String username;

    @NotBlank(message = "닉네임는 필수 항목 입니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 항목 입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 항목 입니다.")
    @Email(message = "이메일은 유효해야 합니다.")
    private String email;

}
