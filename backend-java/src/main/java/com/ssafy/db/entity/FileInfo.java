package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo {
	
	@Id @GeneratedValue
	@Column(name = "file_id")
	private Long id;
	
	@Column(name = "file_name")
	private String fileName;
	
//	@Column(name = "file_origin_name")
//	private String fileOriginName;
//	
//	@Column(name = "file_path")
//	private String filePath;
	
	@Lob
	@Column(name = "data")
	private byte[] data;
	
	@Column(name = "content_type")
	private String contentType;

	@Builder
	public FileInfo(String fileName, byte[] data, String contentType) {
		this.fileName = fileName;
		this.data = data;
		this.contentType = contentType;
	}
	
//	@Builder
//	public FileInfo(Long id, String fileName, String fileOriginName, String filePath, String contentType) {
//		this.id = id;
//		this.fileName = fileName;
//		this.fileOriginName = fileOriginName;
//		this.filePath = filePath;
//		this.contentType = contentType;
//	}
	
}
