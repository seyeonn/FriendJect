package com.ssafy.db.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.JoinedTeamList;
import com.ssafy.db.entity.Member;
import com.ssafy.db.entity.Team;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JoinedTeamListRepository {
	
	private final EntityManager em;
	
	public void save(Member member, Team team) {
		JoinedTeamList teamList = new JoinedTeamList();
		teamList.setMember(member);
		teamList.setTeam(team);
		em.persist(teamList);
	}
}