package com.example.cheongpodo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NicknameChangeRequest {

    @NotBlank(message = "빈칸 입력은 안됩니다.")
    @Size(min = 1, max = 50, message = "닉네임은 1자 이상 50자 이하여야 함")
    private String newNickname;

}
