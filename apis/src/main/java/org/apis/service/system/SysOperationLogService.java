package org.apis.service.system;

import java.util.List;

import org.apis.dao.system.SysOperationLogRepository;
import org.domain.system.SysOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOperationLogService {
	
	@Autowired
	private SysOperationLogRepository sysOperationLogRepository;
	
	public List<SysOperationLog> findAll(){
		return sysOperationLogRepository.findAll();
	}
}
