package org.background.controller.system;

import org.annotation.MenuEx;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.system.SysOperationLogService;
import org.domain.system.SysOperationLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vo.SysOperationLogDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
@RequestMapping("/system")
public class SysOperationLogController {

	private static final Logger logger = LoggerFactory.getLogger(SysOperationLogController.class);
	
	@Autowired
	private SysOperationLogService sysOperationLogService;
	
	@RequiresPermissions("p2p.sysOperationLog.list")
	@MenuEx(code = "p2p.sysOperationLog.list", name = "日志管理", parentCd = "p2p.system.profile", listSort = 120)
	@RequestMapping(value="/sysOperationLogList")
	public String sysOperationLogList(){
		return "system/sysoperationlog_list";
	}
	
	@RequiresPermissions("p2p.sysOperationLog.list")
	@ResponseBody
	@RequestMapping("/sysOperationLogList/data")
	public PageResponse<SysOperationLog> queryByPage(PageRequest request,SysOperationLogDto logDto){
		logger.error("request->{}",request);
		logger.error("logDto->{}",logDto);
		return sysOperationLogService.queryByPage(request, logDto);
	}
	
}
