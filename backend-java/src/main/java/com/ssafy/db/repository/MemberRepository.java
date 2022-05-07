package com.ssafy.db.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Member;

@Repository
public class MemberRepository {
	@PersistenceContext
	EntityManager em;
	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}