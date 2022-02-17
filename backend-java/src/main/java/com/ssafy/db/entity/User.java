package com.ssafy.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter @Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
	
	@Id	@GeneratedValue
	@Column(name = "user_id")
	private Long id; 
	
	private String kakaoId;
	
	private String userEmail;
	private String accessToken;
	private String nickName;
	private String profileUrl;
	private Integer sessionState;
	
	@OneToMany(mappedBy = "user")
	private List<JoinedTeamList> teamList = new ArrayList<>();
 
	public void addTeamList(JoinedTeamList joinedTeamList) {
		teamList.add(joinedTeamList);
		joinedTeamList.setUser(this);
	}
}
