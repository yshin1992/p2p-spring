package org.apis.dao.member;

import org.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByRealCd(String realCd);
	
	Member findByNickName(String nickName);
	
	Member findByEmail(String email);
	
	Member findByPhone(String phone);
	
}
