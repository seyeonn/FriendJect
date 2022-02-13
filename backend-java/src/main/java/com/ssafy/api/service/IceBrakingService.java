package com.ssafy.api.service;

import java.util.List;

import com.ssafy.db.entity.Questions;

public interface IceBrakingService {
	List<Questions> getQuestions() throws Exception;
}
