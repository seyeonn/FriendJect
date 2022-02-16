package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRegisterPostReq {
	private Long teamId;
	private String nickName;
}
