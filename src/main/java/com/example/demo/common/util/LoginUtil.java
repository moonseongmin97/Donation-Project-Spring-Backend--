package com.example.demo.common.util;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.auth.dto.MemberResponseDto;
import com.example.demo.auth.repository.ChatRepository;
import com.example.demo.common.redis.service.RedisServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginUtil {
	   
    @Autowired
    private RedisServiceImpl redisService;
    

    /**
     * Redis에 저장된 로그인 정보를 조회하고 닉네임 추가 후 다시 저장
     * @param uuid 사용자 UUID
     * @param nickName 추가할 닉네임
     * @return MemberResponseDto 업데이트된 사용자 정보
     * @throws Exception 직렬화/역직렬화 예외
     */
    public Map<String, Object> updateNicknameInRedis(String uuid ,String key , Object value) throws Exception {
    	
 	ObjectMapper objectMapper = new ObjectMapper();

 	// 1. 레디스에서 회원 정보 조회 
 	if(!redisService.getTokenKey(uuid)) {
 		return null;
 	}
 	String sessionResult = redisService.getUserIdFromToken(uuid);
 	  
    // 2. 문자열 역직렬화 -> 객체화
 	Map<String, Object> data = objectMapper.readValue(sessionResult, Map.class);

    // 3. 닉네임 추가/수정    
    data.put(key, value);

    System.out.println("오류 발생 전 ==="+data);
    
    // 4. 객체를 문자열로 직렬화
    String updatedJsonValue = objectMapper.writeValueAsString(data);

    System.out.println("오류 발생==="+updatedJsonValue);
    
    // 5. Redis에 업데이트된 사용자 정보 저장
    redisService.saveToken(uuid, updatedJsonValue);

    // 6. 업데이트된 사용자 정보 반환
    return data;
    
    }
	
}
