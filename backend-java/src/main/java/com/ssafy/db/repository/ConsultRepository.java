package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.*;

@Repository
public interface ConsultRepository {
	 // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    SolutionBook getSolution(int no);
    List<ConsultLog> getConsultLog(String q_userId);
    ConsultLog setConsultLog(ConsultLog consultlog);
}
