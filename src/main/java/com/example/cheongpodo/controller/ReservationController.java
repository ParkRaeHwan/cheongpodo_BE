package com.example.cheongpodo.controller;

import com.example.cheongpodo.domain.SpaceReservationAddress;
import com.example.cheongpodo.repository.SpaceReservationAddressRepository;
import com.example.cheongpodo.request.ReservationAddressRequest;
import com.example.cheongpodo.response.ReservationAddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReservationController {

    private final SpaceReservationAddressRepository spaceReservationAddressRepository;

    @Operation(summary = "청년공간 별 예약 주소 조회 API",
            description = "청년 공간 고유 아이디를 받아 예약 주소 조회")
    @GetMapping("/reservationAddress/get")
    public ResponseEntity<?> getReservationAddr(@ModelAttribute ReservationAddressRequest request){
        List<SpaceReservationAddress> bySpaceId = spaceReservationAddressRepository.findBySpaceId(request.getSpaceId());
        List<ReservationAddressResponse> responseList = bySpaceId.stream()
                .map(entity -> {
                    ReservationAddressResponse response = new ReservationAddressResponse();
                    response.setSpaceId(entity.getSpaceId());
                    response.setReservationAddress(entity.getSpaceReservationAddress());
                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
