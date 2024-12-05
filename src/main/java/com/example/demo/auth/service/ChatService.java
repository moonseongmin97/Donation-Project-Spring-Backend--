package com.example.demo.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.auth.dto.ChatUserResponseDto;
import com.example.demo.auth.dto.MemberRequestDto;
import com.example.demo.auth.dto.MemberResponseDto;
import com.example.demo.auth.entity.ChatUserEntity;
import com.example.demo.auth.entity.MemberEntity;
import com.example.demo.auth.repository.ChatRepository;
import com.example.demo.auth.repository.MemberRepository;
import com.example.demo.common.redis.service.RedisServiceImpl;
import com.example.demo.common.util.LoginUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatService {
	
    @Autowired  
    private ChatRepository chatRepository;
    

    @Autowired 
    private LoginUtil loginUtil;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private RedisServiceImpl redisService;
	
	

    /**
     * 채팅 닉네임 설정
     * @param requestDto 회원 가입 정보를 담은 DTO
     * @return Map<String, Object> 상태 메시지를 담은 결과
     */
    public Map<String, Object> setNickName(MemberRequestDto requestDto) {
        Map<String, Object> result = new HashMap<>();
        
        try {
        	ChatUserEntity chatUserEntity = modelMapper.map(requestDto, ChatUserEntity.class);
            // 채팅 중복 확인            
            Map<String, Boolean> entityResult = chatRepository.existsByNickName(chatUserEntity);
            System.out.println("결과===="+entityResult);            
            if (entityResult.get("userIdDuplicate")) {
                result.put("state", false);
                result.put("msg", "회원님은 이미 닉네임을 설정하셨습니다.");                
            }else if(entityResult.get("nickNameDuplicate")) {
                result.put("state", false);
                result.put("msg", "사용 중인 닉네임입니다.");            	
            }else {
                	chatRepository.setNickName(chatUserEntity);
                	System.out.println("결과값은???===="+chatUserEntity);
                	ChatUserResponseDto responseDto = modelMapper.map(requestDto, ChatUserResponseDto.class);
                	//레디스에 닉네임 저장.                
                	//Map<String,Object> response = loginUtil.updateNicknameInRedis(requestDto.getUuid().toString(),"nickName" ,chatUserEntity.getNickName());
                	
                	result.put("data", responseDto);
                    result.put("state", true);
                    result.put("msg", "닉네임 설정 완료.");   
            }
        } catch (Exception e) {
            result.put("state", false);
            result.put("msg", "닉네임 설정중 오류 발생");
            result.put("error", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    

    /**
     * 채팅 닉네임 조회
     * @param requestDto 회원 가입 정보를 담은 DTO
     * @return Map<String, Object> 상태 메시지를 담은 결과
     */
    public Map<String, Object> getNickName(MemberRequestDto requestDto) {
        Map<String, Object> result = new HashMap<>();
        
        try {
        	if(requestDto.getUuid()!=null) {
	        	ChatUserEntity chatUserEntity = modelMapper.map(requestDto, ChatUserEntity.class);
	        	 ChatUserResponseDto responseDto = new ChatUserResponseDto();
	            // 채팅 중복 확인            
	            Optional<ChatUserEntity> entityResult = chatRepository.getNickName(chatUserEntity.getUserId());
	            System.out.println("결과===="+entityResult.isPresent());
	            if(entityResult.isPresent()) {
	             responseDto = modelMapper.map(entityResult.get(), ChatUserResponseDto.class);
	     			result.put("data", responseDto);
	     			result.put("state", true);
	     			result.put("msg", "닉네임 체크 완료.");  
	            }else {
	            	responseDto = modelMapper.map(requestDto, ChatUserResponseDto.class);		
	            		result.put("data", responseDto);
	                    result.put("state", true);
	                    result.put("msg", "로그인은 했지만 닉네임 조회 결과 없다잉.");
	            }
        	}else {        	
                result.put("state", true);
                result.put("msg", "비회원 사용자");
        	}
            
        } catch (Exception e) {
            result.put("state", false);
            result.put("msg", "닉네임 설정중 오류 발생");
            result.put("error", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    
    
}
