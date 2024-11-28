package com.example.cheongpodo.controller;

import com.example.cheongpodo.request.ReviewRequest;
import com.example.cheongpodo.response.ReviewResponse;
import com.example.cheongpodo.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @Operation(summary = "청년 공간 별 후기 작성 API",
            description = "청년 공간 ID, 후기 내용을 받아 리뷰 생성 및 저장")
    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@Valid @RequestBody ReviewRequest reviewRequest,
                                       Authentication authentication) {
        String username = authentication.getName();
        return reviewService.createReview(reviewRequest, username);
    }

    // 특정 청년공간의 모든 리뷰 조회
    @Operation(summary = "특정 청년 공간의 모든 리뷰 조회 API",
            description = "청년 공간 ID를 받아 해당 모든 리뷰 조회 API")
    @GetMapping("/reviews")
    public List<ReviewResponse> getReviews(@RequestParam(required = false) Long youthSpaceId) {
        if (youthSpaceId != null) {
            return reviewService.getReviewsByYouthSpaceId(youthSpaceId);
        }
        return reviewService.getAllReviews();
    }

    // 리뷰 수정
    @Operation(summary = "특정 청년 공간에 있는 하나의 리뷰 수정 API",
            description = "url 주소로 받은 review ID와 그리고 body에 담긴 청년 공간 ID, 수정 리뷰 내용을 받아 리뷰 업데이트 API")
    @PutMapping("/reviews/{reviewId}")
    public ReviewResponse updateReview(@PathVariable Long reviewId,
                                       @Valid @RequestBody ReviewRequest reviewRequest,
                                       Authentication authentication) {
        String username = authentication.getName();
        return reviewService.updateReview(reviewId, reviewRequest, username);
    }

    // 리뷰 삭제
    @Operation(summary = "특정 청년 공간에 있는 하나의 리뷰 삭제 API",
            description = "url 주소로 받은 review ID의 리뷰를 DB에서 삭제합니다.")
    @DeleteMapping("/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long reviewId,
                             Authentication authentication) {
        String username = authentication.getName();
        reviewService.deleteReview(reviewId, username);
    }
}