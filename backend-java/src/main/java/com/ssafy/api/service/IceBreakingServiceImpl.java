package com.ssafy.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.Questions;
import com.ssafy.db.repository.IceBreakingRepository;

@Service("icebreakingService")
@Transactional
public class IceBreakingServiceImpl implements IceBreakingService{

	@Autowired
	IceBreakingRepository icebreakingRepository;
	
	@Override
	public List<Questions> getQuestions() throws Exception {
		
		// 5개 임의 데이터 저장할 배열
		int[] questionNo = new int[5];
		
		// 반환 데이터 저장할 List
		List<Questions> questions = new ArrayList<Questions>();
		
		// 랜덤으로 5개의 번호 추출
		for (int i = 0; i < questionNo.length; i++) {
			questionNo[i] = (int)(Math.random()*50)+1;	
			
			// 중복 제거를 위한 for문
			for(int j =0; j<i; j++) {
				if(questionNo[i] == questionNo[j])
					i--;
			}
			
			questions.add(icebreakingRepository.findByNo(questionNo[i]));
		}
		
		System.out.println("Questions: " + questions);
		
		return questions;
	}

}
