package com.ssafy.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.Team;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.JoinedTeamListRepository;
import com.ssafy.db.repository.TeamRepository;
import com.ssafy.db.repository.UserClassRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl  implements TeamService {
	
	private final TeamRepository teamRepository;
	private final UserClassRepository userClassRepository;
	private final JoinedTeamListRepository joinedTeamListRepository;
	
	@Transactional
	public Long save(TeamRegisterPostReq teamRegisterPostReq) {
		
		User user = userClassRepository.findOne(teamRegisterPostReq.getUserId());
		Team team = new Team();
		team.setName(teamRegisterPostReq.getTeamName());
		team.setTeamNumber(teamRegisterPostReq.getTeamNumber());
		
		joinedTeamListRepository.save(user, team);
		
		teamRepository.save(team);
		System.out.println(team.getId());
		return user.getId();
	}

}
