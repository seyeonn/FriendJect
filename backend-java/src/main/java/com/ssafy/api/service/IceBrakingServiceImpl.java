package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.Questions;
import com.ssafy.db.repository.IceBrakingRepository;

@Service("icebrakingService")
@Transactional
public class IceBrakingServiceImpl implements IceBrakingService{

	@Autowired
	IceBrakingRepository icebrakingRepository;
	
	@Override
	public List<Questions> getQuestions() throws Exception {
		
		// 5개 임의 데이터 저장할 배열
		int[] questionNo = new int[5];
		
		// 반환 데이터 저장할 List
		List<Questions> questions = new ArrayList<Questions>();
		
		// 랜덤으로 5개의 번호 추출
		for (int i = 0; i < questionNo.length; i++) {
			questionNo[i] = (int)(Math.random()*50)+1;	
			System.out.println(questionNo);
			questions.add(icebrakingRepository.findByNo(questionNo[i]));
		}
		
		System.out.println("Questions: " + questions);
		
		return questions;
	}

}