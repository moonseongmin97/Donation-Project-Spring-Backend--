package com.example.demo.donate.payment.repository;

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
public class PaymentRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	    
	 

	

}
