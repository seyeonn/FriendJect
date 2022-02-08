package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.db.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
