package com.ssafy.db.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter
@NoArgsConstructor
public class KakaoInfoDTO {

	private Long id;
	private String nickname;
	private String email;
	private String profile;
}
