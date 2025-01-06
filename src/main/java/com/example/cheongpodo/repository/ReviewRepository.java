package com.example.cheongpodo.repository;

import com.example.cheongpodo.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewByYouthSpaceId(Long youthSpaceId);
}
