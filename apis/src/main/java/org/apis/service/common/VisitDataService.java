package org.apis.service.common;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apis.dao.common.VisitDataRepository;
import org.domain.common.VisitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitDataService {

	@Autowired
	VisitDataRepository visitDataRepository;
	
	/**
	 * 根据IP查询1小时内请求的数据总量
	 * @param visitIp
	 * @return
	 */
	public Integer findCountInOneHourByIp(String visitIp){
		Date end = new Date();
		Date start = DateUtils.addHours(end, -1);
		return visitDataRepository.findCountInOneHourByIp(visitIp, start, end);
	}
	
	/**
	 * 根据会员ID 或手机号查询1小时内请求的数据总量
	 * @param userData
	 * @return
	 */
	public Integer findCountInOneHourByUserData(String userData){
		Date end = new Date();
		Date start = DateUtils.addHours(end, -1);
		return visitDataRepository.findCountInOneHourByIp(userData, start, end);
	}
	
	/**
	 * 保存访问请求信息
	 * @param visitIp
	 * @param visitType
	 * @param userData
	 */
	public void saveVisitData(String visitIp, String visitType, String userData) {
		VisitData vd = new VisitData();
		vd.init();
		vd.setVisitIp(visitIp);
		vd.setVisitType(visitType);
		vd.setUserData(userData);
		visitDataRepository.saveAndFlush(vd);
	}
}
