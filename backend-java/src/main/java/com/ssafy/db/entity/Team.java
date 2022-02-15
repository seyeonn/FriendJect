package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "team")
@NoArgsConstructor
public class Team {

	@Column(name="team_name")
	private String name;
	
	@Id @GeneratedValue
	@Column(name="team_id")
	private Long id;
	
	@Column(name="team_number")
	private String teamNumber;
	
}
