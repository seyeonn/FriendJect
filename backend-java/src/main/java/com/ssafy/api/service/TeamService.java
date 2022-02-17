package com.ssafy.api.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.api.request.JoinTeamPostReq;
import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.JoinedTeamList;
import com.ssafy.db.entity.Team;

public interface TeamService {
	Long save (TeamRegisterPostReq teamRegisterPostReq);

	Long join(JoinTeamPostReq joinTeamPostReq) throws Exception;

	List<Team> findByUserId(Long userId);
	
	Optional<Team> findByTeamId(String teamNum);
}
