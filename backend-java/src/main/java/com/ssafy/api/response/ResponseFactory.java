package com.ssafy.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.dto.EmptyDTO;

public class ResponseFactory {
    public static ResponseEntity<? extends BaseResponseBody> ok() {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "성공", new EmptyDTO(true)));
    }
    
    public static ResponseEntity<? extends BaseResponseBody> ok(Object object) {
    	return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "성공", object));
    }

    public static ResponseEntity<? extends BaseResponseBody> created() {
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(201, "생성됨", new EmptyDTO(true)));
    }
    
    public static ResponseEntity<? extends BaseResponseBody> noContent() {
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(BaseResponseBody.of(204, "존재하지 않음", new EmptyDTO(true)));
    }
    
    public static ResponseEntity<? extends BaseResponseBody> unauthorized() {
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(401, "권한이 없음", new EmptyDTO(true)));
    }

    public static ResponseEntity<? extends BaseResponseBody> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(BaseResponseBody.of(403, "금지됨", new EmptyDTO(true)));
    }
    
    public static ResponseEntity<? extends BaseResponseBody> notFound() {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponseBody.of(404, "찾을 수 없음", new EmptyDTO(true)));
    }

    public static ResponseEntity<? extends BaseResponseBody> conflict() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(BaseResponseBody.of(409, "충돌", new EmptyDTO(true)));
    }
    
    public static ResponseEntity<? extends BaseResponseBody> expectationFailed() {
    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(BaseResponseBody.of(417, "요청 거부됨", new EmptyDTO(true)));
    }

    public static ResponseEntity<? extends BaseResponseBody> internalServerError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(500, "서버 에러", new EmptyDTO(true)));
    }

    public static ResponseEntity<? extends BaseResponseBody> serviceUnavailable() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(BaseResponseBody.of(503, "요청 처리 불가", new EmptyDTO(true)));
    }
}