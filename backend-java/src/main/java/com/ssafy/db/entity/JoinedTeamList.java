package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter @IdClass(JoinedTeamListId.class)
@Table(name = "joinedteamlist")
public class JoinedTeamList {
	
	@Id
	private Long id;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
}
