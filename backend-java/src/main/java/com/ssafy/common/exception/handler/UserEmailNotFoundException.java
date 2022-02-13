package com.ssafy.common.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ssafy.api.response.ResponseFactory;
import com.ssafy.common.model.response.BaseResponseBody;

@ControllerAdvice
public class UserEmailNotFoundException extends ResponseEntityExceptionHandler{
	  public ResponseEntity<? extends BaseResponseBody> userEmailNotFoundException(UsernameNotFoundException exc) {
	    return ResponseFactory.expectationFailed();
	  }
}
