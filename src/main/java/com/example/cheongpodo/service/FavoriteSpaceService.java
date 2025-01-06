package com.example.cheongpodo.service;

import com.example.cheongpodo.domain.User;
import com.example.cheongpodo.exception.ResourceNotFoundException;
import com.example.cheongpodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteSpaceService {

    private final UserRepository userRepository;
    @Transactional
    public void addFavoriteSpace(String username, Long spaceId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없음."));
        List<Long> favoriteSpaces = user.getFavoriteSpaces();
        if (!favoriteSpaces.contains(spaceId)) { // 중복 방지
            favoriteSpaces.add(spaceId);
        } else {
            throw new IllegalArgumentException("이미 즐겨찾기에 추가된 공간입니다.");
        }
    }
    @Transactional
    public void removeFavoriteSpace(String username, Long spaceId){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("사용자를 찾을 수 없음.")
        );
        List<Long> favoriteSpaces = user.getFavoriteSpaces();
        if (favoriteSpaces.contains(spaceId)) {
            favoriteSpaces.remove(spaceId);
        } else {
            throw new IllegalArgumentException("즐겨찾기에 없는 공간입니다.");
        }
    }

    @Transactional
    public List<Long> getFavoriteSpace(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("사용자를 찾을 수 없음.")
        );
        return user.getFavoriteSpaces();
    }
}
