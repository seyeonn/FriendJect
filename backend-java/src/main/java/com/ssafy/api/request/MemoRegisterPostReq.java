package com.ssafy.api.request;

import com.ssafy.db.entity.MemoStatus;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MemoRegisterPostRequest")
public class MemoRegisterPostReq {
	String title;
	String content;
	MemoStatus status;
}
