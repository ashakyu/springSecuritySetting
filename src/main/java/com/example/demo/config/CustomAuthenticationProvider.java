package com.example.demo.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	/*
	 * AuthenticationManager는 전달받은 UsernamePasswordToken응ㄹ 순차적으로 AuthenticationProvider들에게 전달하여
	 * 실제 인증의 과정을 수행해야 하며, 실제 인증에 대한 부분은 authenticate 함수에 작성을 해줘야 한다.
	 * SpringSecurity에서는 Username으로 DB에서 데이터를 조회한 다음에, 비밀번호의 일치 여부를 검사하는 방식으로 작동을 한다.
	 * 그렇기 때문에 먼저 UsernamePasswordToken 토큰으로부터 아이디를 조회해야 한다.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String userEmail = token.getName();
		return token;
	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
