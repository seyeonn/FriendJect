package com.ssafy.db.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.api.request.TeamRegisterPostReq;
import com.ssafy.db.entity.JoinedTeamList;
import com.ssafy.db.entity.Memo;
import com.ssafy.db.entity.Team;
import com.ssafy.db.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
public interface JoinedTeamListRepository  extends JpaRepository<JoinedTeamList, Long> {
}