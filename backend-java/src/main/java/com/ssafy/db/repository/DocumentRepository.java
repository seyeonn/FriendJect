package com.ssafy.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.db.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{
	List<Document> findByTeamIdOrderByIdDesc(String teamId);

}
