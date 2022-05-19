package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	/*
	 * 여기서 정적 자원들에 대해 Security 적용을 예외 한다.
	 * configure 메소드를 통해 어떤 요청에 대해서는 로그인을 요구하고, 어떤 요청에 대해서 로그인을 요구하지 않을지 설정한다.
	 * form 기반의 로그인을 활용하는 경우 로그인 URL, 로그인 성공시 전달할 URL, 로그인 실패 URL 등에 대해서 설정한다.
	 */
	
	//정적 자원에 대해서는 Security를 적용하지 않는다.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
		// /about 요청에 대해서는 로그인을 요구함
			.antMatchers("/about").authenticated()
		// /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
			.antMatchers("/admin").hasRole("ADMIN")
		// 나머지 요청에 대해서는 로그인을 요구하지 않음
			.anyRequest().permitAll()
			.and()
			//로그인 하는 경우에 대해 설정한다
		.formLogin()
			//login 페이지를 제공하는 URL을 설정함
			.loginPage("/user/loginView")
			//login 성공 URL을 설정함
			.successForwardUrl("/index")
			//login 실패 URL을 설정함
			.failureForwardUrl("/index")
			.permitAll()
			.and()
			.addFilterBefore(customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//CustomAuthentitcationFilter를 빈으로 등록하는 과정에서 UserName파라미터와 UserPassword 파라미터를 설정할 수 있다.
	// 이 과정을 거치면 UsernamePasswordToken이 발급된다.
	//그리고 발급받은 token을 Authentication Manager에게 전달한다. Manager은 실제로 인증을 처리할 여러개의 AuthenticationProvider를 가지고 있다.
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/user/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		
		return customAuthenticationFilter;
	}

	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		// TODO Auto-generated method stub
		return new CustomLoginSuccessHandler();
	}
}
