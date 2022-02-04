package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

}
