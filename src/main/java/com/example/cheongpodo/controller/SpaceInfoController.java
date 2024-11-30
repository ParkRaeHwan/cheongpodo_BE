package com.example.cheongpodo.controller;

import com.example.cheongpodo.service.SpaceInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpaceInfoController {

    private final SpaceInfoService spaceInfoService;

    @Operation(summary = "청년 공간 예약 이메일 발송 API",
            description = "청년 공간 고유 ID, 이메일 내용을 받아 청년 공간 관계자 이메일로 전송")
    @PostMapping("spaceInfo/send-email")
    public ResponseEntity<String> sendEmail(
            @RequestParam Long spaceId,
            @RequestParam String content) {
        String result = spaceInfoService.sendEmail(spaceId, content);
        return ResponseEntity.ok(result);
    }
}