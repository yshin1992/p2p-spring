package org.p2p.service.member;

import org.domain.member.MemberOperationLog;
import org.p2p.dao.InsertDao;
import org.p2p.dao.SelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberOperationLogService {

	@Autowired
	private SelectDao selectDao;
	
	@Autowired
	private InsertDao insertDao;
	
	public void save(MemberOperationLog log){
		insertDao.insertForObject("memberOperationLog.insert", log);
	}
}
