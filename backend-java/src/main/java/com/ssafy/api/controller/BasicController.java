package com.ssafy.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {
	
	@GetMapping("a")
	public String ad(String b) {
		b = "hello";
		return b;
	}

}
