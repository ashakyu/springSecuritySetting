package com.group.app.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user/*") //User폴더 이하의 JSP파일에 접근 가능
@Log4j	//Debugging, lombok.jar을 설치 후 의존설정해야지 어노테이션 사용 가능
public class LoginController {

	//아무나 들어올 수 있음
	@GetMapping("all")
	public void goAll() {
		log.info("enter All Page...");
	}
	
	@GetMapping("/member")
	public void goMember() {
		//log test
		log.info("enter Member Page...");
	}
	
	@GetMapping("/admin")
	public void goAdmin() {
		log.info("enter Admin Page...");
	}
	
}
