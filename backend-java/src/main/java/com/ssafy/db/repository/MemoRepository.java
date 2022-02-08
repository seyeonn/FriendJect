package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.MemoStatus;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
	List<Memo> findByStatus(MemoStatus memoStatus);
}
