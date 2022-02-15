package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeamRegisterPostReq {
	private Long memberId;
	private String teamName;
	private Long teamNumber;
}
