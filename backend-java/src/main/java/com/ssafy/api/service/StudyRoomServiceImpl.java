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

	/* 열람실에 입장한 유저의 정보를 가져와서 해당 userid가 0인지 확인(새로 들어온 유저 확인) 후 db에 entryuser 저장*/
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

	/* userid로 user 조회 - 데이터 return */
	@Override
	public Studyroom getUserByUserId(String userid) {
		return studyroomRepository.findByUserid(userid).get();
	}

	/* userid로 user를 찾아서 time 업데이트 */
	@Override
	   public Studyroom saveTime(String userid, int time) {
	      Studyroom user = getUserByUserId(userid);
	      user.setTime(time);
	      return studyroomRepository.save(user);
	   }

	/* db에 저장된 데이터를 time 기준으로 내림차순으로 정렬한 후 가장 상위에 있는 데이터만 조회 */
	@Override
	   public String getStudyBest() {
	      
	      List<Studyroom> list = studyroomRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
	      System.out.println(list.get(0).getUserid());
	      return list.get(0).getUserid();
	   }
}
