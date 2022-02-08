package com.ssafy.db.dto;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoDTO {
	private String title;
	private String content;
	private MemoStatus status;

	public Memo toEntity() {
		return Memo.builder()
				.title(title)
				.content(content)
				.status(status)
				.build();
	}
}
