package com.ssafy.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.api.request.MemoGetListReq;
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
	
	private final EntityManager em;

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
	
	public List<Memo> findMemoOfTeam(MemoGetListReq memoGetListReq) {
		Long team_id = memoGetListReq.getTeamId();
		MemoStatus status = memoGetListReq.getMemoStatus();
		String jpql = "select m from Memo m where m.teamId = :team_id and m.status = :status";
		List<Memo> memoList = em.createQuery(jpql, Memo.class).setParameter("team_id",team_id).setParameter("status",status).getResultList();
		
		for (Memo m : memoList) {
			System.out.println(m.getContent());
		}
		
		return memoList;
	}

	@Override
	public List<Memo> findMemoOfTeam(MemoStatus memoStatus, Long teamId) {

		String jpql = "select m from Memo m where m.teamId = :team_id and m.status = :status";
		List<Memo> memoList = em.createQuery(jpql, Memo.class).setParameter("team_id",teamId).setParameter("status", memoStatus).getResultList();
		
		for (Memo m : memoList) {
			System.out.println(m.getContent());
		}
		
		return memoList;
	}
}
