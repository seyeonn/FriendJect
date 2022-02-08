package com.ssafy.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.StudyRoomService;
import com.ssafy.db.entity.Studyroom;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/studyroom")
public class StudyRoomController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	StudyRoomService studyroomService;



	// db에 이미 있는 userid이면 생성 x
	// url 입장하면 entryuser 감지 및 데이터베이스에 등록
	@PostMapping
	@ApiOperation(value="member 등록 서비스")
	public ResponseEntity<String> regMember(@RequestBody @ApiParam(value = "유저 정보.", required = true)Studyroom entryUser) throws Exception {
		logger.info("insetMember - 호출");

		if (studyroomService.entryUser(entryUser) != null) {
			System.out.println("입장 id 성공");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	
}
