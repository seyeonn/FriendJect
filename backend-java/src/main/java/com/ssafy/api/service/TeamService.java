package com.ssafy.api.service;

import java.util.List;

import com.ssafy.api.request.JoinTeamPostReq;
import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.JoinedTeamList;
import com.ssafy.db.entity.Team;

public interface TeamService {
	Long save (TeamRegisterPostReq teamRegisterPostReq);

	Long join(JoinTeamPostReq joinTeamPostReq);

	List<Team> findByUserId(Long userId);
}
