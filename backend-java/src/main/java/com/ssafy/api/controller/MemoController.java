package com.ssafy.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.request.MemoRegisterPostReq;
import com.ssafy.api.response.MemoRes;
import com.ssafy.api.service.MemoService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.dto.MemoDTO;
import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

@RestController
@RequestMapping("projectroom")
@CrossOrigin("http://localhost:8081")
public class MemoController {
	@Autowired
	MemoService memoService;
	
	@PostMapping("/memo")
	public ResponseEntity<? extends BaseResponseBody> uploadMemo(@RequestBody MemoDTO memoInfo) {
		try {
			Long id = memoService.save(memoInfo);
			return ResponseEntity.status(200).body(BaseResponseBody.of(200, id +" is registered successfully"));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(417).body(BaseResponseBody.of(417, "failed"));
		}	
	}
	
	@GetMapping("/memo")
	public ResponseEntity<List<MemoRes>> getMemoList(@RequestParam("status") MemoStatus memoStatus) {
		List<Memo> findMemos = memoService.findMemos(memoStatus);
		List<MemoRes> memoRes = findMemos.stream()
				.map(m -> new MemoRes(m))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(memoRes);
	}
	
	@GetMapping("/memo/{memoId}")
	public ResponseEntity<MemoRes> getMemo(@PathVariable Long memoId) {

		Memo memo = memoService.findOne(memoId);
		MemoRes memoRes = new MemoRes(memo);
		return ResponseEntity.status(HttpStatus.OK).body(memoRes);
	}
	
	@PutMapping("/memo/{memoId}")
	public ResponseEntity<? extends BaseResponseBody> updateMemo(@PathVariable Long memoId, @RequestBody MemoRegisterPostReq memoReq) {
		// TODO : 존재하지 않는 메모 에러처리
		
		memoService.updateMemo(memoId, memoReq);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "수정 완료"));
	}
	
	@DeleteMapping("/memo/{memoId}")
	public ResponseEntity<? extends BaseResponseBody> deleteMemo(@PathVariable Long memoId) {

		memoService.deleteMemo(memoId);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "삭제 완료"));
	}
	
}
