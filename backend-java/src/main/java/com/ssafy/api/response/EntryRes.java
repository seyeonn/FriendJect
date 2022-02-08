package com.ssafy.api.response;

import com.ssafy.db.entity.Studyroom;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ApiModel("EntryResponse")
public class EntryRes {
	
	@ApiModelProperty(name="User ID")
	String userId;
	


	public static EntryRes of(String userid) {
		EntryRes res = new EntryRes();
		res.setUserId(userid);
		return res;
	}
	
}
