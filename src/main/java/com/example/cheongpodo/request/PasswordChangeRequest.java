package com.example.cheongpodo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {

    @NotBlank(message = "현재 비밀번호는 필수 입력")
    private String currentPassword;

    @NotBlank(message = "새 비밀번호는 필수 입력 사항")
    private String newPassword;

    @NotBlank(message = "비밀번호 확인은 필수 입력 항목.")
    private String confirmNewPassword;
}
