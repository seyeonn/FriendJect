package com.ssafy.db.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final EntityManager em;
	
	public Member findOne(Long id) {
		return em.find(Member.class, id); 
	}
}