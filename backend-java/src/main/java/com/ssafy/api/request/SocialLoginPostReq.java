package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialLoginPostReq {
	String code;
	String username;
}
