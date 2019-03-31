package org.apis.controller.system;

import java.util.List;

import org.apis.service.system.SysOperationLogService;
import org.domain.system.SysOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysOperationLogController {

	@Autowired
	SysOperationLogService sysOperationLogService;
	
	@RequestMapping("/sysLogList")
	public List<SysOperationLog> querySysLogList(){
		return sysOperationLogService.findAll();
	}
}
