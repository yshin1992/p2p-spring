package org.dao.hibernate.system;

import org.dao.hibernate.AbstractDao;
import org.domain.system.SysOperationLog;
import org.vo.SysOperationLogDto;

import pagination.PageRequest;
import pagination.PageResponse;

public interface SysOperationLogDao extends AbstractDao<SysOperationLog> {

	public PageResponse<SysOperationLog> queryByPage(PageRequest request,SysOperationLogDto logDto);
	
}
