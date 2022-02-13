package com.ssafy.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.response.KakaoUserRes;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.KakaoUserInfo;
import com.ssafy.db.entity.UserDetailsImpl;

@RestController
@RequestMapping
public class SocialController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("login/kakao/{code}")
    public ResponseEntity<? extends BaseResponseBody> createAuthenticationTokenByKakao(@PathVariable String code) throws Exception {
		//api 인증을 통해 얻어온 code값 받아오기
		KakaoUserInfo userInfo = userService.kakaoLogin(code);
		KakaoUserRes userRes = new KakaoUserRes(
				userInfo.getKakaoId(), 
				userInfo.getNickname(), 
				userInfo.getEmail(), 
				userInfo.getProfile()
		);
		
		return ResponseFactory.ok(userRes);
    }
	
	@GetMapping("/")
	public void test(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		System.out.println(userDetails.getUsername());
		model.addAttribute("username", userDetails.getUsername()); 
	}
	
	@GetMapping("hello")
	public String hello() {
		return "test";
	}
}
