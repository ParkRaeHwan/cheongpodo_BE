package com.example.cheongpodo.controller;

import com.example.cheongpodo.dto.UserDto;
import com.example.cheongpodo.request.LoginRequest;
import com.example.cheongpodo.response.LoginResponse;
import com.example.cheongpodo.service.UserService;
import com.example.cheongpodo.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @Operation(summary = "회원가입 API 입니다.",
            description = "아이디, 닉네임, 비밀번호, 이메일을 받아 회원가입 합니다.")
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto) {
        try {
            userService.registerUser(userDto);
            // JSON 응답 객체 생성
            Map<String, Object> response = new HashMap<>();
            response.put("message", "회원가입이 성공적으로 처리되었습니다.");
            // JSON 응답 반환
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "로그인 API 입니다.",
            description = "아이디, 비밀번호를 받아 로그인, 성공 시 JWT Token 발급")
    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        try{
            //인증 시도
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    ));
            // 인증 성공시 토큰 생성
            String jwt = jwtUtil.generateToken(loginRequest.getUsername());

            return ResponseEntity.ok(new LoginResponse(jwt));

        }catch(BadCredentialsException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }


}
