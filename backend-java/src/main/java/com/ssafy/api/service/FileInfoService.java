package com.ssafy.api.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.db.entity.FileInfo;

public interface FileInfoService {
	FileInfo save(MultipartFile file) throws IOException;
	FileInfo findOne(String itemId);
	Stream<FileInfo> findFiles();
	//  public Resource load(String filename);
//
//  public void deleteAll();
//
//  public Stream<Path> loadAll();
}