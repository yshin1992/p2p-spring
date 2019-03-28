package org.business.system.impl;

import org.business.AbstractServiceImpl;
import org.business.system.SysOperationLogService;
import org.dao.hibernate.system.SysOperationLogDao;
import org.domain.system.SysOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.SysOperationLogDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Service(value="sysOperationLogService")
public class SysOperationLogServiceImpl extends AbstractServiceImpl<SysOperationLog>
		implements SysOperationLogService {

	@Autowired
	private SysOperationLogDao sysOperationLogDao;
	
	@Override
	public PageResponse<SysOperationLog> queryByPage(PageRequest request,
			SysOperationLogDto logDto) {
		return sysOperationLogDao.queryByPage(request, logDto);
	}


}
