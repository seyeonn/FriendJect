package com.ssafy.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.db.entity.Studyroom;
import com.ssafy.db.repository.StudyRoomRepository;

@Service("studyroomService")
public class StudyRoomServiceImpl implements StudyRoomService{

	@Autowired
	StudyRoomRepository studyroomRepository;

	@Override
	public Studyroom entryUser(Studyroom userEntryInfo) {
		if(studyroomRepository.countByUserid(userEntryInfo.getUserid()) == 0) {
			Studyroom user = new Studyroom();
			user.setUserid(userEntryInfo.getUserid());
			user.setTime(userEntryInfo.getTime());
			return studyroomRepository.save(user);
		}
		else {
			return null;
		}
	}

	@Override
	public Studyroom getUserByUserId(String userid) {
		return studyroomRepository.findByUserid(userid).get();
	}


	@Override
	   public Studyroom saveTime(String userid, int time) {
	      Studyroom user = getUserByUserId(userid);
	      user.setTime(time);
	      return studyroomRepository.save(user);
	   }

	@Override
	   public String getStudyBest() {
	      
	      List<Studyroom> list = studyroomRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
	      System.out.println(list.get(0).getUserid());
	      return list.get(0).getUserid();
	   }
}
