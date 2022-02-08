package com.ssafy.api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import lombok.AllArgsConstructor;
import lombok.var;

@RestController
@AllArgsConstructor
@RequestMapping("/meetingroom/document")
@CrossOrigin("*")
public class DocumentController {
	
	DocumentRepository documentRepoisitory;
	
	@GetMapping
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> result = new ArrayList<>();
		documentRepoisitory.findAll().forEach(documentList -> {
			Map<String, Object> obj = new HashMap<>();
			obj.put("id", documentList.getId());
			obj.put("title", documentList.getTitle());
			obj.put("meeting_time", documentList.getMeetingTime());
			result.add(obj);
		});
		return result;
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> document(@PathVariable int id) {
		var option = documentRepoisitory.findById(id);
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
	public int post(@RequestBody Map<String, Object> body) {
		return documentRepoisitory.save(Document.builder()
				.title(body.get("title").toString())
				.content(body.get("content").toString())
				.meetingTime(LocalDateTime.now())
				.build()).getId();
	}
	
	
	@PutMapping("/{id}")
	public Map<String, Object> update(@PathVariable int id, @RequestBody Map<String, Object> body) {
		var option = documentRepoisitory.findById(id);
		if (!option.isPresent())
			return null;
		Document document = option.get();
		document.setTitle(body.get("title").toString());
		document.setContent(body.get("content").toString());
		documentRepoisitory.save(document);
		return body;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		documentRepoisitory.deleteById(id);
	}
	
}
