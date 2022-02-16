package com.ssafy.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id", columnDefinition = "INT UNSIGNED")
	private Integer id;
	
	@Column(length = 100)
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private LocalDateTime meetingTime;
	
	@Builder
	public Document(String title, String content, LocalDateTime meetingTime) {
		this.title = title;
		this.content = content;
		this.meetingTime = meetingTime;
	}
	

}
