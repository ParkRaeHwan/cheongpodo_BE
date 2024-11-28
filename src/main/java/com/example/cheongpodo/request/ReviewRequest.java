package com.example.cheongpodo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @NotNull(message = "청년 공간 ID는 필수 정보 입니다.")
    private Long youthSpaceId;

    @NotBlank(message = "후기 내용은 필수 정보 입니다.")
    @Size(max = 1000, message = "후기 내용은 1000자 이하여야 합니다.")
    private String content;

}
