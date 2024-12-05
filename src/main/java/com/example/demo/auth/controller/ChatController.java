package com.example.demo.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.dto.MemberDto;
import com.example.demo.auth.dto.MemberRequestDto;
import com.example.demo.auth.service.ChatService;
import com.example.demo.common.redis.service.RedisServiceImpl;
import com.example.demo.common.response.ApiResponse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private RedisServiceImpl redisService;
    
    @Autowired
    private ChatService chatService;
	
	 /**
     * 채팅 닉네임 설정 확인
     * 설명 -채팅 닉네임 설정하는 api 
     *
     * @param chatDto 채팅 정보를 담은 DTO
     * @param req HttpServletRequest 요청 객체
     * @param res HttpServletResponse 응답 객체
     * @return 채팅명 설정 체크 후 사용자 정보 및 메시지를 담은 Map 객체
     */	
    @PostMapping("/setNickName")
    public ResponseEntity setNickName(@RequestBody MemberRequestDto member , MemberRequestDto memberDto  ,HttpServletRequest request,  HttpServletResponse res) throws Exception {
    	try {
    		memberDto.setNickName(member.getNickName());
    		//닉네임 설정		    			    
    		Map<String, Object> result =chatService.setNickName(memberDto);    	    		    		
            ApiResponse response = new ApiResponse((boolean)result.get("state"), result.get("msg").toString() , result.get("data") );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "서버 에러로 닉네임 설정 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	            
        }
    	
    }

	 /**
     * 채팅 닉네임 조회
     * 설명 -채팅 닉네임 조회하는 api 
     *
     * @param chatDto 채팅 정보를 담은 DTO
     * @param req HttpServletRequest 요청 객체
     * @param res HttpServletResponse 응답 객체
     * @return 채팅명 조회
     */	
    @PostMapping("/nickCheck")
    public ResponseEntity chatNameCheck( MemberRequestDto memberDto  ,HttpServletRequest request,  HttpServletResponse res) throws Exception {
    	try {
    		//닉네임 설정		    			    
    		Map<String, Object> result =chatService.getNickName(memberDto);    	    		    		
            ApiResponse response = new ApiResponse((boolean)result.get("state"), result.get("msg").toString() , result.get("data") );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "서버 에러로 닉네임 설정 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);	            
        }
    	
    }
    
}
