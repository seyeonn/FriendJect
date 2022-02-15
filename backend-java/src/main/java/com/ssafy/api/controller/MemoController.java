package com.ssafy.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.ssafy.api.request.MemoListRegisterPostReq;
import com.ssafy.api.request.MemoRegisterPostReq;
import com.ssafy.api.response.MemoRes;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.MemoService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.dto.MemoDTO;
import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/projectroom")
@Api(value = "메모보드", tags = {"프로젝트실"})
@CrossOrigin("*")
public class MemoController {
	@Autowired
	MemoService memoService;
	
	@PostMapping("/memo")
	@ApiOperation(value = "메모 개별 작성", notes = "<strong> 메모 </strong> 를 작성한다. ") 
	public ResponseEntity<? extends BaseResponseBody> uploadMemo(@RequestBody MemoDTO memoInfo) {
		try {
			Long id = memoService.save(memoInfo);
			return ResponseFactory.ok();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseFactory.forbidden();
		}	
	}
	
	@PostMapping("/memo/list")
	@ApiOperation(value = "메모 리스트 작성", notes = "<strong> 메모를 리스트로 </strong> 작성한다. ") 
	public ResponseEntity<? extends BaseResponseBody> uploadMemoList(@RequestBody List<MemoListRegisterPostReq> memoList, @RequestParam("status") MemoStatus memoStatus) throws IOException {
		List<Memo> findMemos = memoService.findMemos(memoStatus);
		
		if (findMemos.size() < memoList.size()) { // memoStatus에 해당하는 메모 리스트가 줄어들었을 때
			for (MemoListRegisterPostReq m : memoList) { 
				boolean flag = false;
				for (Memo memo : findMemos) {
					if (memo.getId() == m.getId()) flag = true;
				}
				if (!flag) {
					memoService.changeMemoStatus(m.getId(), memoStatus);
					return ResponseFactory.ok();
				}
			}
		} 
		return ResponseFactory.badRequest();
	}
	
	@GetMapping("/memo")
	@ApiOperation(value = "메모 조회", notes = "<strong> 메모를 조회 </strong> 한다. ") 
	public ResponseEntity<? extends BaseResponseBody> getMemoList(@RequestParam("status") MemoStatus memoStatus) {
		List<Memo> findMemos = memoService.findMemos(memoStatus);
		List<MemoRes> memoRes = findMemos.stream()
				.map(m -> new MemoRes(m))
				.collect(Collectors.toList());

		return ResponseFactory.ok(memoRes);
	}
	
	@GetMapping("/memo/{memoId}")
	@ApiOperation(value = "메모 상세 조회", notes = "<strong> 특정 메모를 조회 </strong> 한다. ") 
	public ResponseEntity<? extends BaseResponseBody> getMemo(@PathVariable Long memoId) {

		Memo memo = memoService.findOne(memoId);
		MemoRes memoRes = new MemoRes(memo);
		return ResponseFactory.ok(memoRes);
	}
	
	@PutMapping("/memo/{memoId}")
	@ApiOperation(value = "메모 수정", notes = "<strong> 특정 메모를 수정 </strong> 한다. ") 
	public ResponseEntity<? extends BaseResponseBody> updateMemo(@PathVariable Long memoId, @RequestBody MemoRegisterPostReq memoReq) {
		
		memoService.updateMemo(memoId, memoReq);
		
		return ResponseFactory.ok();
	}
	
	@DeleteMapping("/memo/{memoId}")
	@ApiOperation(value = "메모 삭제", notes = "<strong> 특정 메모를 삭제 </strong> 한다. ") 
	public ResponseEntity<? extends BaseResponseBody> deleteMemo(@PathVariable Long memoId) {

		memoService.deleteMemo(memoId);
		
		return ResponseFactory.ok();
	}
	
}
