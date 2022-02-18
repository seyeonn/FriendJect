package com.ssafy.api.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRegisterPostReq {
	private Long teamId;
	private String nickName;
	
	private MultipartFile file;
}
