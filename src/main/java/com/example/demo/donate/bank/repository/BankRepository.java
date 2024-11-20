package com.example.demo.donate.bank.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.auth.entity.MemberEntity;
import com.example.demo.donate.bank.entity.BankEntity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Repository
public class BankRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
    // 모든 은행 리스트 조회
    public List<BankEntity> findAllBanks()  throws Exception{
        String jpql = "SELECT b FROM BankEntity b";
                
        List<BankEntity> query = entityManager.createQuery(jpql, BankEntity.class).getResultList();
        return query;
    }

    // 은행 ID로 특정 은행 조회
    public BankEntity findById(Long id) {
        return entityManager.find(BankEntity.class, id);
    }

    // 은행 추가 (예시로 새로운 은행을 추가)
    public void save(BankEntity bankEntity) {
        entityManager.persist(bankEntity);
    }
	    
	 

	

}
