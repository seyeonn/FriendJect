package com.ssafy.db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class VotLog {
	
	@Id
	private int userId;
	private String title;
	private String result;
	private String date;

}
