package com.ssafy.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id	@GeneratedValue
	@Column(name = "member_id")
	private Long id; 
	 
	@Column(name = "member_name") 
	private String memberName; 
		
	@OneToMany(mappedBy = "member")
	private List<JoinedTeamList> teamList = new ArrayList<>();
	

}
