package com.ssafy.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.ConsultService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/consultroom")
@CrossOrigin("http://localhost:8081")
@Api(value = "상담실", tags = {"상담실"})
public class ConsultController {
	
	@Autowired
	ConsultService consultService;
	
	@GetMapping("/book")
	@ApiOperation(value = "해결의 책", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public SolutionBook book() throws Exception {
		//랜덤 숫자 리턴
		Random rand = new Random();
		int no = rand.nextInt(128)+1;
		
		return consultService.getSolutionBook(no);
	}
	
	@GetMapping("/consult")
	@ResponseBody
	public List<ConsultLog> getConsult(@RequestParam("userId") String userId) throws Exception {
		System.out.println(userId);
		return consultService.getConsultLog(userId);
	}
	
	
	@PostMapping("/consult")
	@ApiOperation(value = "상담 기록 저장") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<? extends BaseResponseBody> setConsult(
			@RequestBody @ApiParam(value="상담기록 저장", required = true) ConsultLog consultlog) throws Exception {
		
		Optional<ConsultLog> consultLog = Optional.ofNullable(consultService.setConsultLog(consultlog));
		return ResponseFactory.ok();
	}
	
	
}


