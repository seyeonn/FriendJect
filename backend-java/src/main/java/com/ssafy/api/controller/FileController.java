package com.ssafy.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.service.FileInfoService;
import com.ssafy.common.model.response.BaseResponseBody;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("projectroom")
@CrossOrigin("http://localhost:8081")
public class FileController {

  @Autowired
  FileInfoService storageService;

//  @PostMapping("/upload")
//  public ResponseEntity<FileRes> uploadFile(@RequestParam("file") MultipartFile file) {
//    String message = "";
//    try {
//      storageService.save(file);
//      message = "Uploaded the file successfully: " + file.getOriginalFilename() + file.getSize();
//      return ResponseEntity.status(HttpStatus.OK).body(new FileRes(message));
//    } catch (Exception e) {
//      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileRes(message));
//    }
//  }
  
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

//  @GetMapping("/files")
//  public ResponseEntity<List<FileInfo>> getListFiles() {
//    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
//    	path.toUri();
//      String filename = path.getFileName().toString();
//      String url = MvcUriComponentsBuilder
//          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
//
//      return new FileInfo(filename, url);
//    }).collect(Collectors.toList());
//
//    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//  }
//
//  @GetMapping("/files/{filename:.+}")
//  @ResponseBody
//  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//    Resource file = storageService.load(filename);
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//  }
}