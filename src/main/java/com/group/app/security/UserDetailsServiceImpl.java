package com.group.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group.app.users.Member;
import com.group.app.users.MemberRepository;
import com.group.app.users.Role;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException(memberId));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (memberId.equals("admin")) {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(member.getMemberId(), member.getPassword(), grantedAuthorities);
    }
	/*
	 * 입력받은 memberId를 통해 DB에서 member정보를 가져오고 원하는 방식대로 Set<GrantedAuthority>를 통해 
	 * 권한을 부여한다. 여기서는 memberId가 "admin"일 경우 'ADMIN' 권한을 주고 아닐 경우 "MEMBER"권한을 주었다. 마지막에는
	 * SecurityUserDetails에 정의된 User 인스턴스에 가져온 Member 정보를 담아 반환해 주었다. 해당 반환값에
	 * 정상적인 UserDetail가 반환되면 인증이 성공하고 정상적이지 않은 값이 반환되면 인증에 실패한다. 
	 */
	
}
