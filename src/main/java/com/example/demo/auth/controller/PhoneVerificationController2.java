package com.example.demo.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/api/phone-verification")
public class PhoneVerificationController2 {

    @PostMapping("/request")
    public ResponseEntity<Map<String, String>> requestVerification(@RequestBody Map<String, String> requestData) {
        String clientId = requestData.get("clientId");
        String clientSecret = requestData.get("clientSecret");

        // NICE API 요청 URL
        String niceApiUrl = "https://nice-check-service-url"; // NICE API 기본 URL

        try {
            RestTemplate restTemplate = new RestTemplate();

            // NICE API 요청 파라미터
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("client_id", clientId);
            requestBody.put("client_secret", clientSecret);
            requestBody.put("redirect_uri", "http://localhost:3000/verification-result"); // React의 결과 페이지

            // NICE API 호출
            ResponseEntity<Map> response = restTemplate.postForEntity(niceApiUrl, requestBody, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, String> responseBody = response.getBody();
                assert responseBody != null;

                // 인증 URL 전달
                Map<String, String> result = new HashMap<>();
                result.put("verificationUrl", (String) responseBody.get("authUrl"));
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
