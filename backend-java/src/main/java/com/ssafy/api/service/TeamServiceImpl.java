package com.ssafy.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.Member;
import com.ssafy.db.entity.Team;
import com.ssafy.db.repository.JoinedTeamListRepository;
import com.ssafy.db.repository.MemberRepository;
import com.ssafy.db.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamServiceImpl  implements TeamService {
	
	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;
	private final JoinedTeamListRepository joinedTeamListRepository;
	
	@Transactional
	public Long save(TeamRegisterPostReq teamRegisterPostReq) {
		
		Member member = memberRepository.findOne(teamRegisterPostReq.getMemberId());
		Team team = new Team();
		team.setName(teamRegisterPostReq.getTeamName());
		team.setTeamNumber(teamRegisterPostReq.getTeamNumber());
		
		joinedTeamListRepository.save(member, team);
		
		teamRepository.save(team);
		return member.getId();
	}

}
