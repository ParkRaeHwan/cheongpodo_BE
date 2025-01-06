package com.example.cheongpodo.service;

import com.example.cheongpodo.domain.Review;
import com.example.cheongpodo.domain.User;
import com.example.cheongpodo.exception.ResourceNotFoundException;
import com.example.cheongpodo.exception.UnauthorizedException;
import com.example.cheongpodo.repository.ReviewRepository;
import com.example.cheongpodo.repository.UserRepository;
import com.example.cheongpodo.request.ReviewRequest;
import com.example.cheongpodo.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewResponse createReview(ReviewRequest reviewRequest, String username) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        User user = byUsername.get();

        Review review = Review.builder().youthSpaceId(reviewRequest.getYouthSpaceId())
                .user(user)
                .content(reviewRequest.getContent())
                .build();

        Review savedReview = reviewRepository.save(review);
        return mapToResponse(savedReview);
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByYouthSpaceId(Long youthSpaceId){
        List<Review> reviews = reviewRepository.findReviewByYouthSpaceId(youthSpaceId);
        return reviews.stream().map(
                this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReviewResponse updateReview (Long reviewId, ReviewRequest reviewRequest, String username){
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoResourceFoundException("리뷰를 찾을 수 없습니다 => " + reviewId));
        if (!review.getUser().getUsername().equals(username)) {
            throw new UnauthorizedException("현재 사용자는 해당 후기의 권한이 없습니다.");
        }
        review.setContent(reviewRequest.getContent());
        review.setUpdateAt(LocalDateTime.now());
        Review savedReview = reviewRepository.save(review);

        return mapToResponse(savedReview);
    }

    @Transactional
    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 리뷰를 찾을 수 없습니다 " + reviewId));
        if (!review.getUser().getUsername().equals(username)) {
            throw new UnauthorizedException("현재 사용자는 해당 후기의 권한이 없습니다.");
        }
        reviewRepository.delete(review);
    }

    public List<ReviewResponse> getAllReviews(){
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(
                this::mapToResponse
        ).collect(Collectors.toList());
    }

    private ReviewResponse mapToResponse(Review review){
        return ReviewResponse.builder()
                .id(review.getId())
                .youthSpaceId(review.getYouthSpaceId())
                .username(review.getUser().getUsername())
                .nickname(review.getUser().getNickname())
                .content(review.getContent())
                .createAt(review.getCreateAt())
                .updateAt(review.getUpdateAt())
                .build();
    }

}
