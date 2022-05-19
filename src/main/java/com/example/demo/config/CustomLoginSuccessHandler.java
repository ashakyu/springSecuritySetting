package com.example.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	/*
		Filter가 수행된 후에 처리될 Handler Bean으로 등록하고 핸들러로 추가해주어야 한다.
		CustoLoginSuccessHandler는 AuthenticationProvider를 통해 인증이 성공될 경우 처리되는데,
		/about요청으로 redirect 되도록 해주었다. 
		세션을 활용하여 로그인 성공시 반환된 Authentication 객체를 SecurityContextHolder의 Contextw에 저장해주어야 한다.
		나중에 사용자의 정보를 꺼낼 경우에도 SecurityContextHolder의 context에서 조회하면 된다. 
	 */

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		SecurityContextHolder.getContext().setAuthentication(authentication);
		response.sendRedirect("/about");
	}
}
