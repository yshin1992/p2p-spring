package org.apis.dao.member;

import org.domain.member.Member;
import org.domain.member.MemberIntegral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberIntegralRepository extends JpaRepository<MemberIntegral, String> {

	public MemberIntegral findByMember(Member member);
	
}
