package com.example.cheongpodo.repository;

import com.example.cheongpodo.domain.SpaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceInfoRepository extends JpaRepository<SpaceInfo, Long> {
    SpaceInfo findBySpaceId(Long spaceId);
}
