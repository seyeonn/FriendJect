package com.ssafy.db.dto;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemoDTO {
	private Long id;
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
