package com.ssafy.api.request;

import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoListRegisterPostReq {
	Long id;
	String title;
	String content;
	MemoStatus status;
}
