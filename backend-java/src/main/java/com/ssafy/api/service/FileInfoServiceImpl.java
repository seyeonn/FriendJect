package com.ssafy.api.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.db.dto.FileInfoDTO;
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
	
//	@Transactional
//	public void save(MultipartFile[] files) {
////		LocalDate today = LocalDate.now();
////        String path = "article/" + today.getYear() + "/" + today.getMonth() + "/" + today.getDayOfMonth();
//
//
//		if (!files[0].isEmpty()) {
//			String realPath = servletContext.getRealPath("/upload");
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = realPath + File.separator + today;
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfoDTO> fileInfos = new ArrayList<FileInfoDTO>();
//			for (MultipartFile mfile : files) {
//				String originalFileName = mfile.getOriginalFilename();
//				FileInfoDTO fileInfoDto = new FileInfoDTO();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto = FileInfoDTO.builder()
//	                        .id(null)
//	                        .fileName(saveFileName)
//	                        .fileOriginName(mfile.getOriginalFilename())
//	                        .filePath(saveFolder)
//	                        .contentType(mfile.getContentType())
//	                        .build();
//					
//					FileInfo fe = FileInfo.builder()
//								.id(null)
//								.fileName(fileInfoDto.getFileName())
//								.fileOriginName(fileInfoDto.getFileOriginName())
//								.filePath(fileInfoDto.getFilePath())
//								.contentType(fileInfoDto.getContentType())
//		                        .build();
//					try {
//						mfile.transferTo(new File(folder, saveFileName));
//					} catch (IllegalStateException | IOException e) {
//						e.printStackTrace();
//					}
//					fileRepository.save(fe);
//				}
//				fileInfos.add(fileInfoDto);
//			}
//		}
//	}
	
	
}