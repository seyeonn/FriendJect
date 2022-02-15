package com.ssafy.api.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.IceBreakingService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Questions;
import com.ssafy.db.entity.SolutionBook;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/icebreaking")
@Api(value = "아이스브레이킹")
@CrossOrigin("*")
public class IceBreakingController {

	@Autowired
	IceBreakingService icebreakingService;
	
	@GetMapping("/questions")
	@ApiOperation(value = "질문 리스트")
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public List<Questions> book() throws Exception {
		
		return icebreakingService.getQuestions();
	}
	
}
