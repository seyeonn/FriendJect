package com.ssafy.db.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Member;
import com.ssafy.db.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserClassRepository {
	
	private final EntityManager em;
	
	public User findOne(Long id) {
		return em.find(User.class, id); 
	}
}