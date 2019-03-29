package org.dao.hibernate.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.SysOperationLogDao;
import org.domain.system.SysOperationLog;
import org.domain.system.User;
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

	@Override
	public void saveSysLogByModifySystemConfig(User operator,
			String modifyContent, String oldContent) {
		SysOperationLog sysOperationLog = new SysOperationLog();
		sysOperationLog.init();
		sysOperationLog.setOperateObj("OperateObj");
		sysOperationLog.setOperateObjName("系统配置");// 产品名称
		sysOperationLog.setOperateType(SysOperationLog.OPERATE_TYPE_UPDATE);
		sysOperationLog.setOperatorId(operator.getUserId());// 操作人ID
		sysOperationLog.setOperatorAccount(operator.getUserCd());// 操作人账号
		sysOperationLog.setOperatorType("20");// 操作人类型 20:表示后台操作人员
		sysOperationLog.setOperateContent(modifyContent);// 操作内容
		sysOperationLog.setOperateObjType(SysOperationLog.OBJ_TYPE_OTHER);// 操作对象类型
		sysOperationLog.setOperateTime(new Date());// 操作时间
		sysOperationLog.setRemark(oldContent.length() <= 200 ? oldContent : oldContent.substring(0, 199));// 备注
		save(sysOperationLog);
	}


}
