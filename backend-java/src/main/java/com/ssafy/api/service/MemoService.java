package com.ssafy.api.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.api.request.MemoRegisterPostReq;
import com.ssafy.api.response.MemoRes;
import com.ssafy.db.dto.MemoDTO;
import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

public interface MemoService {

	Long save(MemoDTO memoInfo) throws IOException;

	List<MemoRes> findMemos(MemoStatus memoStatus);

	Memo findOne(Long memoId);

	void updateMemo(Long memoId, MemoRegisterPostReq memoReq);

	void deleteMemo(Long memoId);

}
