package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

}
