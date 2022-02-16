package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeamRegisterPostReq {
	private Long userId;
	private String teamName;
	private String teamNum;
}
