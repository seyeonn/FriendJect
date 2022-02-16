package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinTeamPostReq {
	Long userId;
	String teamNumber;
}
