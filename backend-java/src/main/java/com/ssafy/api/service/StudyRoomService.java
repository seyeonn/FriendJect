package com.ssafy.api.service;

import com.ssafy.db.entity.Studyroom;

public interface StudyRoomService {
	Studyroom entryUser(Studyroom userEntryInfo);
	Studyroom getUserByUserId(String userid);
	
}
