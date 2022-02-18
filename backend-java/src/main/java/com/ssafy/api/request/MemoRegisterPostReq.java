package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MemoRegisterPostRequest")
public class MemoRegisterPostReq {
	String title;
	String content;
}
