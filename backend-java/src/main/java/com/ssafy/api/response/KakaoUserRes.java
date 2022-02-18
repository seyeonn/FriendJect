package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@ApiModel("KakaoUserResponse")
@RequiredArgsConstructor
public class KakaoUserRes {
	private Long kakaoId;
	private String nickname;
	private String email;
	private String profile;
	
	public KakaoUserRes(Long kakaoId, String nickname, String email, String profile) {
		this.kakaoId = kakaoId;
		this.nickname = nickname;
		this.email = email;
		this.profile = profile;
	}
}
