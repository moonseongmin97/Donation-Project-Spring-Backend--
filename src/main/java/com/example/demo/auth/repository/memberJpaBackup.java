package com.example.demo.auth.repository;
//import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.auth.entity.MemberEntity;

import java.util.List;
import java.util.Map;

@Service
public class memberJpaBackup {
   @Autowired
    public static String findMember(Map<String, Object> param) {
        System.out.println("jpa class");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();


       // Member FindMember = null;
        try {
           // Member member = new Member();

            List<MemberEntity> members = em.createQuery( "select m from Member m where id = 1" , MemberEntity.class)
                    //.setParameter("userId", "2")
                    .getResultList();
            System.out.println("멤버---"+members);
            for(MemberEntity a :members){
                //System.out.println("리스트 제발=="+a.getName());
                //System.out.println("리스트 제발22=="+a.getName());
            }



           //FindMember = em.find(Member.class, member.getId());


            //System.out.println("조회값 전 =="+FindMember);
/*            FindMember = em.createQuery("SELECT m.id FROM Member m where m.id= :id", Member.class)
                        .setParameter("id", member.getId())
                        .getSingleResult();*/
           // System.out.println("죄회 결과값===="+FindMember);



            //FindMember.setName("updateReact?");


            //  em.persist();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        return "ㄷ";//FindMember.getName();
    };
}
