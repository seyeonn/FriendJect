package com.ssafy.api.response;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoRes {
	private Long id;
	private String title;
	private String content;
	private MemoStatus status;
    
	public MemoRes(Memo entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.status = entity.getStatus();
	} 
    
}
