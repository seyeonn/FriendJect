package com.ssafy.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ssafy.api.response.FileRes;
import com.ssafy.api.response.FileResMessage;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.common.model.response.BaseResponseBody;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<? extends BaseResponseBody> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseFactory.expectationFailed();
  }
}