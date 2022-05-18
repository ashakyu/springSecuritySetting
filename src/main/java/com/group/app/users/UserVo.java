package com.group.app.users;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserVo {
	
	private Long id;
	private String memberId;
	private String password;
	
	public UserVo(Long id, String memberId, String password) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.password = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
