package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "team")
@NoArgsConstructor
public class Team {

	@Id @GeneratedValue
	@Column(name="team_id")
	private Long id;
	
	@Column(name="team_name")
	private String name;
	
	@Column(name="team_number")
	private Long teamNumber;
	
	@OneToMany(mappedBy = "team")
	private List<JoinedTeamList> teamList = new ArrayList<>();
	
}
