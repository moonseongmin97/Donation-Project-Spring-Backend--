package com.example.demo.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/phone-verification")
public class PhoneVerificationController {

    @PostMapping("/request")
    public ResponseEntity<Map<String, String>> requestVerification(@RequestBody Map<String, String> requestData) {
        String clientId = requestData.get("clientId");
        String clientSecret = requestData.get("clientSecret");

        // 클라이언트 ID와 시크릿 키 확인
        if (clientId == null || clientSecret == null) {
            return ResponseEntity.status(400).body(Map.of("error", "Client ID 또는 Secret Key가 누락되었습니다."));
        }

        // Mock 응답 생성
        String mockVerificationUrl = "http://mock-nice-verification.com/auth?token=12345"; // 테스트 URL
        Map<String, String> response = new HashMap<>();
        response.put("verificationUrl", mockVerificationUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/result")
    public ResponseEntity<Map<String, String>> processVerificationResult(@RequestParam("token") String token) {
        // Mock 결과 처리
        Map<String, String> result = new HashMap<>();
        result.put("resultCode", "success");
        result.put("message", "본인인증이 성공적으로 완료되었습니다.");
        return ResponseEntity.ok(result);
    }
}
