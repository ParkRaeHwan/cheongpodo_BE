package com.example.cheongpodo.service;

import com.example.cheongpodo.domain.SpaceInfo;
import com.example.cheongpodo.repository.SpaceInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceInfoService {

    private final JavaMailSender javaMailSender;

    private final SpaceInfoRepository spaceInfoRepository;

    public String sendEmail(Long spaceId, String content) {
        SpaceInfo spaceInfo = spaceInfoRepository.findBySpaceId(spaceId);

        if (spaceInfo == null) {
            return "No email found for spaceId " + spaceId;
        }

        String recipientEmail = spaceInfo.getSpaceEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("[청공] 청년공간 사용 예약 신청 입니다."); // 메일 제목
        message.setText(content);
        message.setFrom("miriyouth@gmail.com");

        try {
            javaMailSender.send(message);
            return "Email sent to " + recipientEmail;
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
