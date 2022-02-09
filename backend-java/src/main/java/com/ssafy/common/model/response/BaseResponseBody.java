package com.ssafy.common.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 서버 요청에대한 기본 응답값(바디) 정의.
 */
@Getter @Setter
@ApiModel("BaseResponseBody")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "BaseResponseBody")
public class BaseResponseBody<T> {
	
	@ApiModelProperty(name="응답 코드", example = "200")
	Integer statusCode = null;
	@ApiModelProperty(name="응답 메시지", example = "정상")
	String message = null;
	
	@Schema(name = "응답 데이터")
	private T data;
	
	public BaseResponseBody(Integer statusCode){
		this.statusCode = statusCode;
	}
	
	public static BaseResponseBody of(Integer statusCode, String message, Object data) {
		return new BaseResponseBody(statusCode, message, data);
	}
}
