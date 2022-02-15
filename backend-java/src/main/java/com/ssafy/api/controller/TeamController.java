package com.ssafy.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.TeamService;
import com.ssafy.common.model.response.BaseResponseBody;

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
}
