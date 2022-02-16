package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo extends BaseTimeEntity {
	
	@Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "file_id")
	private String id;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Lob
	@Column(name = "data")
	private byte[] data;

	@Column(name = "team_id")
	private Long teamId;
	
	@Builder
	public FileInfo(String fileName, byte[] data, String contentType) {
		this.fileName = fileName;
		this.data = data;
		this.contentType = contentType;
	}
}
