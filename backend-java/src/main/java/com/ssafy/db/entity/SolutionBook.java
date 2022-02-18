package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class SolutionBook {
	
	@Column(name = "no")
	@Id
	private int no;
	
	@Column(name = "text")
	private String text;
}
