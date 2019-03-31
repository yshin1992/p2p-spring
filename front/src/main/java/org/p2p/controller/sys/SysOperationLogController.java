package org.p2p.controller.sys;

import java.util.List;

import org.domain.system.SysOperationLog;
import org.p2p.service.sys.SysOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class SysOperationLogController {
	
	@Autowired
	private SysOperationLogService sysOperationLogService;
	
	@ResponseBody
	@RequestMapping("/sysLogList")
	public List<SysOperationLog> getSysLogList(){
		return sysOperationLogService.querySysLogList();
	}
}
