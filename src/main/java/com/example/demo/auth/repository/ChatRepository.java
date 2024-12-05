package com.example.demo.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.auth.entity.ChatUserEntity;
import com.example.demo.auth.entity.MemberEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Repository
public class ChatRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	


	    // ID로 회원 조회
	    public Optional<MemberEntity> findById(Long id) {                                         
	    	MemberEntity member = entityManager.find(MemberEntity.class, id);
	    	System.out.println("레파지토리 탐");
	        return Optional.ofNullable(member);
	    }
	    
	 // JPQL을 사용해 userId로 닉네임 가져오기 (최신 등록 순으로 정렬)
	    public Optional<ChatUserEntity> getNickName(Long userId) {
	        try {
	            // JPQL 쿼리 작성: 최신 등록 순으로 정렬
	            String jpql = "SELECT c FROM ChatUserEntity c WHERE c.userId = :userId ORDER BY c.createdAt DESC";
	            
	            // JPQL 실행 및 결과 가져오기
	            ChatUserEntity nickName = entityManager.createQuery(jpql, ChatUserEntity.class)
	                                                   .setParameter("userId", userId)
	                                                   .setMaxResults(1) // 가장 최신의 1개 결과만 가져옴
	                                                   .getSingleResult();
	            
	            return Optional.of(nickName); // 결과가 있으면 Optional로 반환
	        } catch (NoResultException e) {
	            return Optional.empty(); // 결과가 없으면 Optional.empty()
	        }
	    }
	    
	    //닉네임 사용 여부 확인
	    public Map<String, Boolean> existsByNickName(ChatUserEntity chatUserEntity) throws Exception {
	        Map<String, Boolean> result = new HashMap<>();

	        // userId 중복 여부 확인
	        String jpqlUserId = "SELECT COUNT(c) > 0 FROM ChatUserEntity c WHERE c.userId = :userId";
	        Boolean isUserIdDuplicate = entityManager.createQuery(jpqlUserId, Boolean.class)
	                //.setParameter("userId", chatUserEntity.getUserId())
	                .setParameter("userId", chatUserEntity.getUserId())
	                .getSingleResult();

	        // nickName 중복 여부 확인
	        String jpqlNickName = "SELECT COUNT(c) > 0 FROM ChatUserEntity c WHERE c.nickName = :nickName";
	        Boolean isNickNameDuplicate = entityManager.createQuery(jpqlNickName, Boolean.class)
	                .setParameter("nickName", chatUserEntity.getNickName())
	                .getSingleResult();

	        result.put("userIdDuplicate", isUserIdDuplicate);
	        result.put("nickNameDuplicate", isNickNameDuplicate);

	        return result;
	    }

	    
		 // 닉네임 설정
	    @Transactional
	    public void setNickName(ChatUserEntity chatUserEntity) {
	            entityManager.persist(chatUserEntity);
	        }


	    // 회원 저장
	    public void save(MemberEntity member) {	    		    
	        entityManager.persist(member);
	    }
	

}
