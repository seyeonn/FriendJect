package com.ssafy.db.dto;

import javax.validation.constraints.NotEmpty;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemoDTO {
	
	private Long id;
	
	@NotEmpty(message = "제목 입력은 필수입니다.")
	private String title;
	@NotEmpty(message = "내용 입력은 필수입니다.")
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
