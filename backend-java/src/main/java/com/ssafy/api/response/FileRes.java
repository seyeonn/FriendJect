package com.ssafy.api.response;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@ApiModel("FileResponse")
@RequiredArgsConstructor
public class FileRes {
	private String id;
	private String name;
	private String url;
	private String type;
	private Long size;
    private LocalDateTime modifiedDate;
	
	public FileRes(String id, String name, String url, String type, Long size, LocalDateTime modifiedDate) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.modifiedDate = modifiedDate;
	}

}
