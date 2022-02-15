package com.ssafy.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.db.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
