package com.ssafy.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.JoinTeamPostReq;
import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.TeamService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Team;

@RestController
@RequestMapping("api/team")
@CrossOrigin("*")
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@PostMapping("/create")
	public ResponseEntity<? extends BaseResponseBody> createTeam(@RequestBody TeamRegisterPostReq teamRegisterPostReq) {
		Long teamNum = teamService.save(teamRegisterPostReq);
		return ResponseFactory.ok(teamNum);	
	}
	
	@PostMapping("/join")
	public ResponseEntity<? extends BaseResponseBody> joinTeam(@RequestBody JoinTeamPostReq joinTeamPostReq) {
		try {
			return ResponseFactory.ok(teamService.join(joinTeamPostReq));	// team id 반환
		}catch (Exception e) {
			return ResponseFactory.internalServerError();
		}
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<? extends BaseResponseBody> getTeamList(@PathVariable Long userId) {
		
		return ResponseFactory.ok(teamService.findByUserId(userId));
	}
	
	@GetMapping("/{teamNum}")
	public ResponseEntity<? extends BaseResponseBody> getOneTeam(@PathVariable String teamNum) {
		try {
			System.out.println("**************teamNumber : "+teamNum);
			Team team = teamService.findByTeamId(teamNum).get();
			System.out.println("$$$$$$$$$$$$$$$$ team :"+team);
			return ResponseFactory.ok(team);
		} catch (Exception e) {
			e.getStackTrace();
			return ResponseFactory.internalServerError();
		}
	}
	
	
}
