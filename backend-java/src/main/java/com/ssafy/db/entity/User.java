package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter @Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id; 
	
	private String kakaoId;
	
	private String userEmail;
	private String accessToken;
	private String nickName;
	private String profileUrl;
//	
//	@OneToMany(mappedBy = "user")
//	private List<JoinedTeamList> teamList = new ArrayList<>();
// 
//	public void addTeamList(JoinedTeamList joinedTeamList) {
//		teamList.add(joinedTeamList);
//		joinedTeamList.setUser(this);
//	}
	
	@Builder 
	public User(String kakaoId, String userEmail, String accessToken, String nickName, String profileUrl) {
		this.kakaoId = kakaoId;
		this.userEmail = userEmail;
		this.accessToken = accessToken;
		this.nickName = nickName;
		this.profileUrl = profileUrl;
		
	}
}
