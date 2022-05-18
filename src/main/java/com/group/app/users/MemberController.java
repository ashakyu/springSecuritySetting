package com.group.app.users;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("/main")
	public String mainPage(@AuthenticationPrincipal User user, Map<String ,Object> model) {
		List<Member> members = memberRepository.findAll();
		//로그인을 통해 인증된 유저 정보는 Security Context Holder에 저장되며 아래와 같이 가져올 수 있다.
		model.put("currentMemberId", user.getUsername());
		model.put("members", members);
		return "homepage";
	}
	
	@GetMapping("/admin")
	public String adminPage(Map<String, Object> model) {
		return "adminpage";
	}
	
	@GetMapping("/member/new")
	public String memberJoinForm(Member memberForm) {
		return "memberJoinForm";
	}
	
	@PostMapping("/member/new")
	public String memberJoin(Member memberForm) {
		//passwordEncoder로 비밀번호 암호화 
		memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
		memberRepository.save(memberForm);	
		return "redirect:/login";
	}
	
}
