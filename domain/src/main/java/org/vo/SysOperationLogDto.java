package org.vo;

import java.util.Date;

import org.domain.system.SysOperationLog;

public class SysOperationLogDto {
	
	private SysOperationLog sysOperationLog;
	
	private Date queryStartTime;
	
	private Date queryEndTime;

	public SysOperationLog getSysOperationLog() {
		return sysOperationLog;
	}

	public void setSysOperationLog(SysOperationLog sysOperationLog) {
		this.sysOperationLog = sysOperationLog;
	}

	public Date getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(Date queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public Date getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	
}
