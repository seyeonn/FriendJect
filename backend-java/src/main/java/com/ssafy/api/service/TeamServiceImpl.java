package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.JoinTeamPostReq;
import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.JoinedTeamList;
import com.ssafy.db.entity.Team;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.JoinedTeamListRepository;
import com.ssafy.db.repository.TeamRepository;
import com.ssafy.db.repository.UserClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
	private final EntityManager em;

	private final TeamRepository teamRepository;
	private final UserClassRepository userClassRepository;
	private final JoinedTeamListRepository joinedTeamListRepository;
	
	@Transactional
	public Long save(TeamRegisterPostReq teamRegisterPostReq) {

		Team team = new Team();
		team.setTeamNumber(teamRegisterPostReq.getTeamNum());
		team.setName(teamRegisterPostReq.getTeamName());
		em.persist(team);

		User user = userClassRepository.findOne(teamRegisterPostReq.getUserId());
		
		JoinedTeamList joinedTeamList = new JoinedTeamList();
		joinedTeamList.setTeam(team);
		joinedTeamList.setUser(user);
		
		em.persist(joinedTeamList);
		
		return user.getId();
	}

	@Transactional
	public Long join(JoinTeamPostReq joinTeamPostReq) {
		
		User user = userClassRepository.findOne(joinTeamPostReq.getUserId());
		
		String team_number = joinTeamPostReq.getTeamNumber().toString();
		String jpql = "select m from Team m where m.teamNumber = :team_number";
		Team team = em.createQuery(jpql, Team.class).setParameter("team_number", team_number).getSingleResult();
		
		
		System.out.println(team.getName() + ", " + team.getTeamNumber());
		System.out.println(user.getNickName());
		System.out.println(user.getId() + ", " + user.getNickName());
		JoinedTeamList joinedTeamList = new JoinedTeamList();
		joinedTeamList.setTeam(team);
		joinedTeamList.setUser(user);
		
		em.persist(joinedTeamList);
		
		return team.getId();
	}
	
	@Override
	public List<Team> findByUserId(Long userId) {
		
		List<Team> team = new ArrayList<>();
		
		TypedQuery<JoinedTeamList> query = em.createQuery("select m from JoinedTeamList m", JoinedTeamList.class);
		
		List<JoinedTeamList> teamList = query.getResultList();
		for (JoinedTeamList j : teamList) {
			if (j.getUser().getId() == userId) {
				team.add(j.getTeam());
				System.out.println(j.getTeam().getName());
			}
		}
		return team;
	}

	public Optional<Team> findByTeamNumber(String teamNumber) {
		return teamRepository.findByTeamNumber(teamNumber);
	}

}
