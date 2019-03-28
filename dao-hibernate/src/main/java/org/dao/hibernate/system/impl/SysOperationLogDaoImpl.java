package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.SysOperationLogDao;
import org.domain.system.SysOperationLog;
import org.springframework.stereotype.Repository;
import org.vo.SysOperationLogDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository(value="sysOperationLogDao")
public class SysOperationLogDaoImpl extends AbstractDaoImpl<SysOperationLog> implements
		SysOperationLogDao {

	@Override
	public PageResponse<SysOperationLog> queryByPage(PageRequest request,
			SysOperationLogDto logDto) {
		StringBuilder HQL = new StringBuilder("from SysOperationLog where 1=1 ");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(logDto.getQueryStartTime()!=null){
			HQL.append(" and operateTime >:queryStartTime");
			condition.put("queryStartTime", logDto.getQueryStartTime());
		}
		if(logDto.getQueryEndTime()!=null){
			HQL.append(" and operateTime <:queryEndTime");
			condition.put("queryEndTime", logDto.getQueryEndTime());
		}
		SysOperationLog log = logDto.getSysOperationLog();
		if(null != log){
			if(log.getOperateType()!=null){
				HQL.append(" and operateType =:operateType"); //操作类型
				condition.put("operateType", log.getOperateType());
			}
			if(StringUtils.isNotEmpty(log.getOperateObjName())){ //操作对象名
				HQL.append(" and operateObjName like :operateObjName");
				condition.put("operateObjName", "%"+log.getOperateObjName()+"%");
			}
			if(StringUtils.isNotEmpty(log.getOperateObjType())){ //操作对象类型
				HQL.append(" and operateObjType =:operateObjType");
				condition.put("operateObjType", log.getOperateObjType());
			}
			if(StringUtils.isNotEmpty(log.getOperatorAccount())){ //操作人员
				HQL.append(" and operatorAccount =:operatorAccount");
				condition.put("operatorAccount", log.getOperatorAccount());
			}
		}
		HQL.append(" order by createTime desc");
		return super.queryPageByHQL(HQL.toString(), condition, request);
	}


}
