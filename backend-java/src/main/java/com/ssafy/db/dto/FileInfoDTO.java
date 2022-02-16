package com.ssafy.db.dto;

import com.ssafy.db.entity.FileInfo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileInfoDTO {

	private String id;
	private String fileName;
	private byte[] data;
	private String contentType;
	
	@Builder
	public FileInfoDTO(String id, String fileName, byte[] data, String contentType) {
		this.id = id;
		this.fileName = fileName;
		this.data = data;
		this.contentType = contentType;
	}
	
	public FileInfo toEntity() {
		FileInfo build = FileInfo.builder()
						.fileName(fileName)
						.data(data)
						.contentType(contentType)
						.build();
		return build;
	}
	
}
