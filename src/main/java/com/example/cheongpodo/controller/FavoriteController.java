package com.example.cheongpodo.controller;

import com.example.cheongpodo.dto.FavoriteSpaceDTO;
import com.example.cheongpodo.request.FavoriteSpaceRequest;
import com.example.cheongpodo.response.FavoriteSpaceResponse;
import com.example.cheongpodo.service.FavoriteSpaceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteSpaceService favoriteSpaceService;
    @Operation(summary = "즐겨찾기 추가 API",
            description = "청년 공간 고유 ID를 저장")
    @PostMapping("/favorites/add")
    public ResponseEntity<?> addFavoriteSpace(@RequestBody FavoriteSpaceRequest request,
                                              Authentication authentication) {
        try {
            favoriteSpaceService.addFavoriteSpace(authentication.getName(), request.getSpaceId());
            return ResponseEntity.ok(new FavoriteSpaceResponse(
                    "성공적으로 즐겨찾기에 추가되었습니다.",
                    request.getSpaceId(),
                    "success"
            ));
        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new FavoriteSpaceResponse(
                            e.getMessage(),
                            request.getSpaceId(),
                            "error"
                    ));
        }
    }
    @Operation(summary = "즐겨찾기 삭제 API",
            description = "청년 공간 고유 ID를 즐겨찾기에서 삭제")
    @DeleteMapping("/favorites/remove")
    public ResponseEntity<?> removeFavoriteSpace(@RequestBody FavoriteSpaceRequest request,
                                                 Authentication authentication) {
        try {
            favoriteSpaceService.removeFavoriteSpace(authentication.getName(), request.getSpaceId());
            return ResponseEntity.ok(new FavoriteSpaceResponse(
                    "성공적으로 삭제되었습니다.",
                    request.getSpaceId(),
                    "success"
            ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new FavoriteSpaceResponse(
                            e.getMessage(),
                            request.getSpaceId(),
                            "error"
                    ));
        }
    }
    @Operation(summary = "즐겨찾기 조회 API",
            description = "청년 공간 고유 ID 즐겨찾기 정보 조회")
    @GetMapping("/favorites/get")
    public ResponseEntity<FavoriteSpaceDTO> getFavoriteSpaceId(Authentication authentication) {
        List<Long> favoriteSpace = favoriteSpaceService.getFavoriteSpace(authentication.getName());
        FavoriteSpaceDTO response = new FavoriteSpaceDTO(authentication.getName(),favoriteSpace);
        return ResponseEntity.ok(response);
    }
}
