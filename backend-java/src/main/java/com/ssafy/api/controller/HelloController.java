package com.ssafy.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hello")
public class HelloController {
	@GetMapping
	public String hello() {
		return "hello\\n!!!!!!!!!!!!hello\\n!!!!!!!!!!!!hello\\n!!!!!!!!!!!!hello\\n!!!!!!!!!!!!";
	}
}
