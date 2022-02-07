package com.ssafy.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileResMessage {
	private String message;

	public FileResMessage(String message) {
		this.message = message;
	}
}
