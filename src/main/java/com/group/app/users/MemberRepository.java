package com.group.app.users;

import java.lang.reflect.Member;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> fildByMemberId(String memberId);
}
