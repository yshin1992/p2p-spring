package org.p2p.service.member;

import org.domain.member.Member;
import org.p2p.dao.SelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private SelectDao selectDao;
	
	public Member queryMemberByPhone(String phone){
		return selectDao.selectForObject("member.queryByPhone", phone, Member.class);
	}

	public Member queryMemberById(String memberId){
		return selectDao.selectForObject("member.queryById", memberId, Member.class);
	}
}
