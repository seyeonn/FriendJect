package com.ssafy.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.api.response.FileRes;
import com.ssafy.api.service.FileInfoService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.FileInfo;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("projectroom")
@CrossOrigin("http://localhost:8081")
public class FileController {

  @Autowired
  FileInfoService storageService;
  
  @PostMapping("/files")
  @ApiResponses({
      @ApiResponse(code = 200, message = "성공"),
      @ApiResponse(code = 417, message = "업로드 실패"),
  })
  public ResponseEntity<? extends BaseResponseBody> uploadMultipleFiles(@RequestParam("file") MultipartFile file) {
	  String message = "";
	  try {
		storageService.save(file);
		message = "Uploaded the file successfully: " + file.getOriginalFilename();
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, message));
	} catch (IOException e) {
		e.printStackTrace();
		return ResponseEntity.status(417).body(BaseResponseBody.of(417, message));
	}	
  }
  
  @GetMapping("/files/{fileId}")
  public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
	  FileInfo FileInfo = storageService.findOne(fileId);

	  return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + FileInfo.getId() + "\"")
        .body(FileInfo.getData());
  }

	@GetMapping("/files")
	public ResponseEntity<List<FileRes>> getFileList() {
		List<FileRes> files = storageService.findFiles().map(file -> {
		      String fileDownloadUri = ServletUriComponentsBuilder
							          .fromCurrentContextPath()
							          .path("/files/")
							          .path(file.getId())
							          .toUriString();

		      return new FileRes(
		    		  file.getId(),
		    		  file.getFileName(),
			          fileDownloadUri,
			          // 업로더 반환
			          file.getContentType(),
			          Long.valueOf(file.getData().length),
			          file.getModifiedDate());
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	}
}