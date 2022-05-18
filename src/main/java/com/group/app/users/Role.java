package com.group.app.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	/*
	 * 유저 정보에 부여할 수 있는 권한이다. MEMBER 와 ADMIN 두 가지 권한을 Enum으로 정의한다.
	 * Role value의 앞에는 "ROLE_" 문자열이 있어야 한다.
	 */
	MEMBER("ROLE_MEMBER"),
	ADMIN("ROLE_ADMIN");
	
	private String value;
}
