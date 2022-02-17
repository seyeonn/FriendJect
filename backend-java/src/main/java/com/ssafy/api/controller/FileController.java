package com.ssafy.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssafy.api.request.FileRegisterPostReq;
import com.ssafy.api.response.FileRes;
import com.ssafy.api.response.ResponseFactory;
import com.ssafy.api.service.FileInfoService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.FileInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "자료실", tags = {"프로젝트실"})
@RestController
@RequestMapping("api/projectroom")
@CrossOrigin("*")
public class FileController {

  @Autowired
  FileInfoService storageService;
  
  @ApiOperation(value = "파일 업로드", notes = "<strong> 파일을 업로드 </strong> 한다. ") 
  @PostMapping(value = "/files", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<? extends BaseResponseBody> uploadMultipleFiles(@ModelAttribute FileRegisterPostReq fileRegisterPostReq) {
		try {
			System.out.println(fileRegisterPostReq.getNickName());
			System.out.println(fileRegisterPostReq.getTeamId());
			storageService.save(fileRegisterPostReq.getFile());
			return ResponseFactory.ok();
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseFactory.forbidden();
		}
	}
  
  @GetMapping("/files/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
		FileInfo fileInfo = storageService.findOne(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getId() + "\"")
				.body(fileInfo.getData());
	}

	@GetMapping("/files")
	@ApiOperation(value = "파일 목록 조회", notes = "<strong> 업로드된 파일 전체 </strong> 를 조회한다. ") 
	public ResponseEntity<? extends BaseResponseBody> getFileList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "modifiedDate");
		List<FileRes> files = storageService.findFiles(sort).map(file -> {
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
	
		return ResponseFactory.ok(files);
	}
}