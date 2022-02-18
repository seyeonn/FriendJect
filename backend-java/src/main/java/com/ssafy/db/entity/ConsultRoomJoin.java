package com.ssafy.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ConsultRoomJoin {
	
	@Column(name = "no")
	@Id
	private int no;
	
	@Column(name = "code")
	private int code;
	
	@Column(name = "type")
	private int type;
	
	@Column(name = "name")
	private String name;
}
