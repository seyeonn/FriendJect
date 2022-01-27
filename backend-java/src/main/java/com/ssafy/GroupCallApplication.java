package com.ssafy;

import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.ssafy.api.service.FilesStorageService;

@SpringBootApplication
public class GroupCallApplication implements CommandLineRunner {
	 @Resource
	  FilesStorageService storageService;
	
	public static void main(String[] args) {
        SpringApplication.run(GroupCallApplication.class, args);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    
    @Override
    public void run(String... arg) throws Exception {
      storageService.deleteAll();
      storageService.init();
    }
}
