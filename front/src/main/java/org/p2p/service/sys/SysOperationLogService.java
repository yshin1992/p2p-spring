package org.p2p.service.sys;

import java.util.List;

import org.domain.system.SysOperationLog;
import org.p2p.service.ServiceSupport;
import org.springframework.stereotype.Service;

@Service
public class SysOperationLogService extends ServiceSupport {

	public List<SysOperationLog> querySysLogList(){
		return getSelectDao().selectForList("sys.querySysOperationLogList");
	}
	
}
