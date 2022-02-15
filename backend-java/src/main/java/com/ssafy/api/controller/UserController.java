package com.ssafy.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/userinfo")
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "유저", tags = {"유저"})
public class UserController {
	
	UserRepository userRepository;
	
	
	@GetMapping
	@ApiOperation(value = "세션에 참여한 회원 조회", notes = "세션에 참여한 회원목록을 조회한다. ")
	public List<String> list() {
		List<String> result = new ArrayList<>();
		userRepository.findAll().forEach(userList -> {
			if (userList.getSessionState().equals(1)) {
				result.add(userList.getKakaoId());
			}
		});
		return result;
	}
	
	@PutMapping("/{kakao_id}")
	@ApiOperation(value = "회원 session 수정", notes = "<strong> 유저 Id로 </strong> session 참여여부를 수정한다. ")
	public Map<String, Object> update(@PathVariable String kakao_id, @RequestBody Map<String, Object> body) {
		Optional<User> option = userRepository.findByKakaoId(kakao_id);
		if (!option.isPresent())
			return null;
		User user = option.get();
		user.setSessionState((Integer) body.get("session_state"));
		userRepository.save(user);
		return body;
	}
	
}


