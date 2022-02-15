package com.ssafy.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.KakaoService;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Kakao 로그인 관련 API 요청 처리를 위한 컨트롤러
 */
@CrossOrigin("*")
@RequestMapping("/login")
@RestController
@Api(value = "카카오톡 간편 가입", tags = {"회원가입 및 로그인"})
public class KakaoController {
    @Autowired
    private KakaoService kakaoService;
    
    @CrossOrigin("*")
    @PostMapping
    @ApiOperation(value = "로그인 및 회원가입", notes = "<strong> 로그인과 동시에 회원가입 </strong> 을 진행한다. ") 
    public ResponseEntity<User> home(@RequestBody Map<String, String> requestData) throws Exception{

    	String code = requestData.get("code");
        System.out.println("###인가코드###" + code);
        HashMap<String, Object> token = kakaoService.getAccessToken(code);
        
        String access_Token = (String) token.get("access_Token");
        
        if(access_Token=="false") {
        	return ResponseEntity.badRequest()
                    .body(new User());
        }
          
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
        
        String userEmail = (String) userInfo.get("email");
        
        System.out.println("###kakao_id#### : " + userInfo.get("kakao_id"));
        System.out.println("###access_Token#### : " + token.get("access_Token"));
        System.out.println("###refresh_Token#### : " + token.get("refresh_Token"));
        System.out.println("###userEmail#### : " + userEmail);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###profile_image#### : " + userInfo.get("profile_image"));
        
        
        // 카카오톡으로 로그인한 email이 user테이블에 존재하는지 확인
        User user = kakaoService.getUserEmail(userInfo);
        
        return ResponseEntity.ok().body(user);
    }

}