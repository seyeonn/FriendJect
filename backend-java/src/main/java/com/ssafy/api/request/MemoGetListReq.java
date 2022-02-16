package com.ssafy.api.request;

import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoGetListReq {
	Long teamId;
	MemoStatus memoStatus;
}
