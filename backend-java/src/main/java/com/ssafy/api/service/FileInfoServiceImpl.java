package com.ssafy.api.service;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.db.entity.FileInfo;
import com.ssafy.db.repository.FileInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileInfoServiceImpl implements FileInfoService {
	
	private final FileInfoRepository fileRepository;
	@Autowired
	private ServletContext servletContext;
	
	@Transactional 
	public FileInfo save(MultipartFile file) throws IOException {
	   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	   FileInfo fileInfo = new FileInfo(fileName, file.getBytes(), file.getContentType());
	   return fileRepository.save(fileInfo);
	}
	
	public FileInfo findOne(String itemId) {
		return fileRepository.findById(itemId).get();
	}

	public Stream<FileInfo> findFiles() {
		return fileRepository.findAll().stream();
	}
	
}