package com.ssafy.api.service;
import java.util.List;
import java.util.Optional;

import com.ssafy.db.entity.*;

public interface ConsultService {
	SolutionBook getSolutionBook(int no) throws Exception;
	ConsultLogList setConsultLog(ConsultLogList consultlog) throws Exception;
	List<ConsultLogList> getConsultLog(String userId) throws Exception;
	
}
