package com.example.demo.auth.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class KakaoAuthController {

    private final String CLIENT_ID = "9a54a16e112189f86832d723ec4e13c0";
    private final String REDIRECT_URI = "http://localhost:3000/KakaoRedirect";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/kakao")
    public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        String accessToken = getAccessToken(code);
        Map<String, Object> userInfo = getUserInfo(accessToken);

        return ResponseEntity.ok(userInfo);
    }

    // 1. Access Token 발급
    private String getAccessToken(String code) {
        String url = "https://kauth.kakao.com/oauth/token"
                + "?grant_type=authorization_code"
                + "&client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&code=" + code;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(url, null, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("AccessToken 파싱 오류", e);
        }
    }

    // 2. 사용자 정보 가져오기
    private Map<String, Object> getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        Map<String, Object> userInfo = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode properties = jsonNode.path("properties");
            JsonNode kakaoAccount = jsonNode.path("kakao_account");

            userInfo.put("nickname", properties.path("nickname").asText());
            userInfo.put("email", kakaoAccount.path("email").asText());
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보 파싱 오류", e);
        }

        return userInfo;
    }
}

