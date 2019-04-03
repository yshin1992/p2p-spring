package org.apis.service.member;

import org.apis.dao.member.MemberOperationLogRepository;
import org.domain.member.MemberOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberOperationLogService {

	@Autowired
	private MemberOperationLogRepository memberOperationLogRepository;
	
	public void save(MemberOperationLog memberOperationLog){
		memberOperationLogRepository.saveAndFlush(memberOperationLog);
	}
}
