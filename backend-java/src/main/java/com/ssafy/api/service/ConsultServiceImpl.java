package com.ssafy.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;

@Service("consultService")
@Transactional
public class ConsultServiceImpl implements ConsultService {
	
	@Autowired
	ConsultRepository consultRepository;
	
	@Autowired
	JpaConsultRepository JpaConsultRepository;
	
	@Override
	public SolutionBook getSolutionBook(int no) throws Exception {
		// TODO Auto-generated method stub
		return JpaConsultRepository.getSolution(no);
			
	}

	@Override
	public ConsultLog setConsultLog(ConsultLog consultlog) throws Exception {
		// TODO Auto-generated method stub
		return JpaConsultRepository.setConsultLog(consultlog);
	}

	@Override
	public List<ConsultLog> getConsultLog(String userId) throws Exception {
		// TODO Auto-generated method stub
		return  JpaConsultRepository.getConsultLog(userId);
	}

	

}
