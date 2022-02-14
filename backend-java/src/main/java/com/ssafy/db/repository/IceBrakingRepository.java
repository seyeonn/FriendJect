package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Questions;

@Repository
public interface IceBrakingRepository extends JpaRepository<Questions, Long>{

	Questions findByNo(int no);

}
