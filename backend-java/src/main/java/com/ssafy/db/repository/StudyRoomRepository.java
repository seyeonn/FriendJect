package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Studyroom;

@Repository
public interface StudyRoomRepository extends JpaRepository<Studyroom, Long>{

	Optional<Studyroom> findByUserid(String userid);

	int countByUserid(String userid);

	



}
