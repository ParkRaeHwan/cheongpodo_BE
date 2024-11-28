package com.example.cheongpodo.controller;

import com.example.cheongpodo.request.NicknameChangeRequest;
import com.example.cheongpodo.request.PasswordChangeRequest;
import com.example.cheongpodo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "닉네임 변경 API",
            description = "새로운 닉네임을 받아 변경")
    @PutMapping("/users/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeNickname(@Valid @RequestBody NicknameChangeRequest request,
                               Authentication authentication) {
        String username = authentication.getName();
        userService.changeNickname(username, request);
    }

    @Operation(summary = "비밀번호 변경 API",
            description = "기존 비밀번호를 입력 받아 검증 후 새로운 비밀번호 입력")
    @PutMapping("/users/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@Valid @RequestBody PasswordChangeRequest request,
                               Authentication authentication) {
        String username = authentication.getName();
        userService.changePassword(username,request);
    }

}
