package com.example.cheongpodo.repository;

import com.example.cheongpodo.domain.SpaceReservationAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceReservationAddressRepository extends JpaRepository<SpaceReservationAddress, Long> {
    List<SpaceReservationAddress> findBySpaceId(Long spaceId);
}
