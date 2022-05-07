package com.ssafy.db.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.ConsultLog;
import com.ssafy.db.entity.SolutionBook;

@Repository
public class JpaConsultRepository implements ConsultRepository {

	private final EntityManager em;
	
	public JpaConsultRepository(EntityManager em) {
		this.em = em;
	}


	@Override
	public SolutionBook getSolution(int no) {
		// TODO Auto-generated method stub
		SolutionBook solutionbook = em.find(SolutionBook.class, no);
		return solutionbook;
	}


	@Override
	public List<ConsultLog> getConsultLog(String q_user_id) {
		String qlString = "select m from ConsultLog m where m.q_user_id = :q_user_id";
		String name = "q_user_id";
		
		List<ConsultLog> log = em.createQuery( qlString, ConsultLog.class)
		.setParameter( name, q_user_id)
		.getResultList();
		
		return log;
	}


	@Override
	public ConsultLog setConsultLog(ConsultLog consultlog) {
		// TODO Auto-generated method stub
		em.persist(consultlog);
		return consultlog;
	}
	
	

}
