package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "kakao_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoUserInfo extends BaseTimeEntity {
	
	@Id @GeneratedValue
	@Column(name = "kakao_user_id")
	private Long id;
	
	@Column(name = "kakao_user_kakaoId")
	private Long kakaoId;
	
	@Column(name = "kakao_user_password")
	private String password;
	
	@Column(name = "kakao_user_nickname")
	private String nickname;
	
	@Column(name = "kakao_user_email")
	private String email;
	
	@Column(name = "kakao_user_profile_url")
	private String profile;
	
	@Column(name = "kakao_user_role")
	@Enumerated(value = EnumType.STRING)
	private UserRole role;
	
	@Builder
	public KakaoUserInfo(Long id, String password, String nickname, String email, String profile, UserRole role) {
		if (id != null) this.kakaoId = id;
		else this.kakaoId = null;
		
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}
}
