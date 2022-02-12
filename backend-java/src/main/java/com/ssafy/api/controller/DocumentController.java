package com.ssafy.api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.db.entity.Document;
import com.ssafy.db.repository.DocumentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(value = "회의록 CRUD", tags = {"회의실"})
@RestController
@AllArgsConstructor
@RequestMapping("/meetingroom/document")
@CrossOrigin("*")
public class DocumentController {
	
	DocumentRepository documentRepoisitory;
	
	@GetMapping
	@ApiOperation(value = "회의록 조회", notes = "<strong> 회의록 전체 </strong> 를 조회한다. ") 
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> result = new ArrayList<>();
		Sort sort = Sort.by(Sort.Direction.DESC, "id"); 
		documentRepoisitory.findAll(sort).forEach(documentList -> {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", documentList.getId());
			obj.put("title", documentList.getTitle());
			obj.put("meeting_time", documentList.getMeetingTime());
			result.add(obj);
		});
		return result;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "회의록 상세 조회", notes = "<strong> 글 Id로 </strong> 상세 회의록을 조회한다. ")
	public Map<String, Object> document(@PathVariable int id) {
		Optional<Document> option = documentRepoisitory.findById(id);
		if (!option.isPresent())
			return null;
		Document document = option.get();
		Map<String, Object> obj = new HashMap<>();
		obj.put("id", document.getId());
		obj.put("title", document.getTitle());
		obj.put("content", document.getContent());
		obj.put("meeting_time", document.getMeetingTime());
		return obj;
	}
	
	@PostMapping
	@ApiOperation(value = "회의록 작성", notes = "<strong> 새로운 회의록 </strong> 을 작성한다. ")
	public int post(@RequestBody Map<String, Object> body) {
		return documentRepoisitory.save(Document.builder()
				.title(body.get("title").toString())
				.content(body.get("content").toString())
				.meetingTime(LocalDateTime.now())
				.build()).getId();
	}
	
	
	@PutMapping("/{id}")
	@ApiOperation(value = "회의록 수정", notes = "<strong> 글 Id로 </strong> 회의록을 수정한다. ")
	public Map<String, Object> update(@PathVariable int id, @RequestBody Map<String, Object> body) {
		Optional<Document> option = documentRepoisitory.findById(id);
		if (!option.isPresent())
			return null;
		Document document = option.get();
		document.setTitle(body.get("title").toString());
		document.setContent(body.get("content").toString());
		documentRepoisitory.save(document);
		return body;
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "회의록 삭제", notes = "<strong> 글 Id로 </strong> 회의록을 삭제한다. ")
	public void delete(@PathVariable int id) {
		documentRepoisitory.deleteById(id);
	}
	
}
