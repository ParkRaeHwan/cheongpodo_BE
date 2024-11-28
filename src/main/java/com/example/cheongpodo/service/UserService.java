package com.example.cheongpodo.service;

import com.example.cheongpodo.domain.User;
import com.example.cheongpodo.dto.UserDto;
import com.example.cheongpodo.exception.BadRequestException;
import com.example.cheongpodo.exception.ResourceNotFoundException;
import com.example.cheongpodo.repository.UserRepository;
import com.example.cheongpodo.request.NicknameChangeRequest;
import com.example.cheongpodo.request.PasswordChangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserDto userDto) {
        // 중복 체크
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        // 사용자 저장
        User user = User.builder()
                .username(userDto.getUsername())
                .nickname(userDto.getNickname())
                .password(encodedPassword)
                .email(userDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void changeNickname(String username, NicknameChangeRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없음"));
        user.setNickname(request.getNewNickname());
        userRepository.save(user);
    }

    @Transactional
    public void changePassword(String username, PasswordChangeRequest request){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없음"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("현재 비밀번호가 일치하지 않습니다");
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new com.example.cheongpodo.exception.BadRequestException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
