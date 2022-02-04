package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@ApiModel("FileResponse")
@RequiredArgsConstructor
public class FileRes {
	
	private String name;
	private String url;
	private String type;
	private Long size;
	
	public FileRes(String name, String url, String type, Long size) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
	}

}
