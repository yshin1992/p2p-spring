package org.business.market.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.business.market.IntegralSetService;
import org.dao.hibernate.system.CategoryAttrDao;
import org.dao.hibernate.system.SysOperationLogDao;
import org.domain.system.CategoryAttr;
import org.domain.system.User;
import org.enums.CategoryAttrEnum;
import org.enums.CategoryEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.IntegralSetDto;

/**
 * 积分设置服务接口
 * @author yanshuai
 *
 */
@Service(value="integralSetService")
public class IntegralSetServiceImpl implements IntegralSetService {

	private static final Logger logger = LoggerFactory.getLogger(IntegralSetServiceImpl.class);
	@Autowired
	private CategoryAttrDao categoryAttrDao;
	
	@Autowired
	private SysOperationLogDao sysOperationLogDao;
	
	@Transactional
	@Override
	public void save(IntegralSetDto dto, User user) {
		logger.debug("积分参数设置保存=====开始 参数传入：{}", dto);
		
		List<CategoryAttr> dropDownDtoList = categoryAttrDao.findByCategoryCd(CategoryEnum.INTEGRALCONFIG.getAttrCd());
		boolean isUpdate = true;
		String value = "";
		StringBuilder oldContentSb = new StringBuilder();
		StringBuilder modifyContentSb = new StringBuilder();
		for (CategoryAttr item : dropDownDtoList) {
			value = "";
			isUpdate = true;
			if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd())) {
				value = dto.getIsUseIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd())) {
				value = dto.getRegisterGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd())) {
				value = dto.getRecommendFriendsGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_LOGINGIVEINTEGRAL.getAttrCd())) {
				value = dto.getLoginGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_FRIENDSINVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getFriendsInvestGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_INVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getInvestGiveIntegral().toString();
			} else if (item.getAttrCd().equals(CategoryAttrEnum.INTEGRAL_MAXINVESTGIVEINTEGRAL.getAttrCd())) {
				value = dto.getMaxInvestGiveIntegral().toString();
			} else {
				isUpdate = false;
			}

			if (isUpdate) {
				CategoryAttr upAttr = categoryAttrDao.findByAttrCd(item.getAttrCd());
				if (!upAttr.getActualval().equals(value)) {
					oldContentSb.append("[" + item.getAttrCd() + ":" + item.getActualval() + "]");
					upAttr.setActualval(value);
					modifyContentSb.append("[" + upAttr.getAttrCd() + ":" + upAttr.getActualval() + "]");
				}
			}
		}
		if (oldContentSb.length() > 0) {
			oldContentSb.insert(0, "[修改前的值]");
			modifyContentSb.insert(0, "[修改后的值]");
			sysOperationLogDao.saveSysLogByModifySystemConfig(user, modifyContentSb.toString(),
					oldContentSb.toString());
		}
		logger.debug("积分参数设置保存=====结束");
	}

}
