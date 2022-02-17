package com.ssafy.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/profile")
@CrossOrigin("*")
public class ProfileController {
	
	UserService userService;
	UserRepository userRepository;
	
	@PatchMapping("/minime/{userEmail}")
	public String select_minime(@PathVariable String userEmail, @RequestBody Map<String, Object> body) {
		User user = userService.findByUserEmail(userEmail);
		user.setProfileUrl(body.get("profileUrl").toString());
		userRepository.save(user);
		return userEmail + " 회원의 프로필사진이 미니미로 변경었습니다";
	}
	
	@PatchMapping("/{userEmail}")
	public String image_insert(@PathVariable String userEmail, @RequestParam(value = "filename", required = false) MultipartFile mFile) throws Exception {
		String upload_path = "\\home\\ubuntu\\ssafy\\userpofileimg\\" + userEmail;
		File uploadFolder = new File(upload_path);
		if(!uploadFolder.exists()) {
			try{
				
				uploadFolder.mkdir(); //폴더 생성합니다.
			    System.out.println(uploadFolder + "폴더가 생성되었습니다.");
		        } 
		        catch(Exception e){
		        	e.getStackTrace();
		        }
		}else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
			File[] files = uploadFolder.listFiles();
			for(File file : files) {
				file.delete();
				System.out.println(file + "파일이 삭제되었습니다.");
			}
			
	        }
		User user = userService.findByUserEmail(userEmail);
		
		mFile.transferTo(new File(upload_path + "\\" + mFile.getOriginalFilename()));  // 경로에 이미지 저장
		user.setProfileUrl("https://i6b202.p.ssafy.io/api/profile/image/" + userEmail + "/" + mFile.getOriginalFilename());	
		userRepository.save(user);
		return userEmail;
	}	
	
	@GetMapping(value = {"image/{userEmail}/{imageName}"}, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userSearch(@PathVariable("userEmail") String userEmail, @PathVariable("imageName") String imageName) throws IOException {
		InputStream imageStream = new FileInputStream("\\home\\ubuntu\\ssafy\\userpofileimg\\" + userEmail + "\\" + imageName);
		byte[] imageByteArray = org.apache.commons.io.IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
}
