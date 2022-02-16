package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ssafy.api.request.MemoRegisterPostReq;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "memo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private Long id;
	
	@Column(name = "memo_title")
	private String title;

	@Column(name = "memo_content")
	private String content;
	
	@Enumerated(EnumType.STRING)
	private MemoStatus status = MemoStatus.TODO;
	
	@Column(name = "team_id")
	private Long teamId;
	
	@Builder
	public Memo(String title, String content, MemoStatus status, Long teamId) {
		this.title = title;
		this.content = content;
		if (status != null) {
			this.status = status;
		}
		this.teamId = teamId;
	}

	/**
	 * 메모 수정
	 */
	public void change(MemoRegisterPostReq memoReq) {
		
		this.setTitle(memoReq.getTitle());
		this.setContent(memoReq.getContent());
		this.setStatus(this.getStatus());
	}
}
