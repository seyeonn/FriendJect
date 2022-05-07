package com.ssafy.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.MemoRegisterPostReq;
import com.ssafy.api.response.MemoRes;
import com.ssafy.db.dto.MemoDTO;
import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;
import com.ssafy.db.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {
	private final MemoRepository memoRepository;

	@Transactional 
	public Long save(MemoDTO memoInfo) throws IOException {
		Memo memo = memoRepository.save(memoInfo.toEntity());
		return memo.getId();
	}

	public List<Memo> findMemos(MemoStatus memoStatus) {
		List<Memo> memo = memoRepository.findByStatus(memoStatus);
		return memo;
	}

	public Memo findOne(Long memoId) {
		return memoRepository.findById(memoId).get();
	}

	@Transactional 
	public void updateMemo(Long memoId, MemoRegisterPostReq memoReq) {
		Memo memo = memoRepository.findById(memoId).get();
		memo.change(memoReq);
	}

	@Transactional 
	public void deleteMemo(Long memoId) {
		memoRepository.deleteById(memoId);
	}

	@Transactional
	public void changeMemoStatus(Long memoId, MemoStatus memoStatus) {
		Memo memo = memoRepository.findById(memoId).get();
		memo.setStatus(memoStatus);
		System.out.println(memo.getStatus());
	}
}
