package org.business.system;

import org.business.AbstractService;
import org.domain.system.SysOperationLog;
import org.vo.SysOperationLogDto;

import pagination.PageRequest;
import pagination.PageResponse;

public interface SysOperationLogService extends AbstractService<SysOperationLog> {

	public PageResponse<SysOperationLog> queryByPage(PageRequest request,SysOperationLogDto logDto);
}
