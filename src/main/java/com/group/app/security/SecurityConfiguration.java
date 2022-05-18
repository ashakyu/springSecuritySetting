package com.group.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	/*
	 * WebSecurityConfigurerAdapter 클래스를 상속받은 뒤 configure(HttpSecurity http) 메소드를 오버라이딩 한다.
	 * 클래스는 @EnablewebSecurity 애노테이션을 붙여준다.
	 */
	
	protected void configure(HttpSecurity http) throws Exception{
		http 
			.authorizeRequests()
				// /member/new 요청은 권한없이 접근이 가능합니다.
				.antMatchers("/member/new").permitAll()
				// /admin 요청은 "ADMIN" ROLE 권한을 가진 사람만 접근할 수 있습니다.
				.antMatchers("/admin").hasRole("ADMIN")
				
				// 그 외 모든 요청은 인증된 사용자만 접근할 수 있습니다.
				.anyRequest().authenticated()
				.and()
			
			//FormLogin 과 logout 기능을 사용할 수 있으며 login 페이지는 권한 없이 접근이 가능하고 login 성공시 /main url 을 요청합니다.
			.formLogin()
				.defaultSuccessUrl("/main")
				.permitAll()
				.and()
			.logout();
			
	}
}
