package com.ssafy.api.response;

import java.time.LocalDateTime;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;

@Getter
public class MemoRes {
	private Long id;
	private String title;
	private String content;
	private MemoStatus status;
	private LocalDateTime createdDate;   
    private LocalDateTime lastModifiedDate;
    
	public MemoRes(Memo entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.status = entity.getStatus();
	} 
    
}
