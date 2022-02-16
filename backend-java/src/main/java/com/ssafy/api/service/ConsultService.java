package com.ssafy.api.service;
import java.util.List;
import java.util.Optional;

import com.ssafy.db.entity.*;

public interface ConsultService {
	SolutionBook getSolutionBook(int no) throws Exception;
	ConsultLog setConsultLog(ConsultLog consultlog) throws Exception;
	List<ConsultLog> getConsultLog(String userId) throws Exception;
}
