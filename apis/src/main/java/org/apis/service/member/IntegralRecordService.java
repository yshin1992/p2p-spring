package org.apis.service.member;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apis.dao.member.IntegralRecordRepository;
import org.domain.member.IntegralRecord;
import org.domain.member.Member;
import org.enums.IntegralRecordEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntegralRecordService {

	private static final Logger logger = LoggerFactory.getLogger(IntegralRecordService.class);
	
	@Autowired
	private IntegralRecordRepository integralRecordRepository;
	
	
	/**
	 * 
	 * @param member
	 *            积分所属会员
	 * @param integralVal
	 *            积分值 根据isAddFlag判断 增加存正值 消耗存负值
	 * @param isAddFlag
	 *            新增或消耗
	 * @param remark
	 *            备注
	 * @param objId
	 *            关联数据主键 0注册:增加:关联memberId 1登录:增加:关联memberId 2推荐好友注册:增加:关联memberId 3投资成功:增加:关联投资订单 4推荐好友投资成功:增加:关联投资订单 5新投资额:增加:关联投资订单 6投资消耗:减少:关联投资订单
	 * @param objType
	 *            关联数据类型 0注册 1登录 2推荐好友注册 3投资成功 4推荐好友投资成功 5新投资额 6投资消耗
	 * @param amount
	 *            *对应面值 可空
	 */
	private void sendIntegral(Member member, Integer integralVal, IntegralRecordEnum isAddFlag, String remark, String objId,
			IntegralRecordEnum objType, BigDecimal amount) {
		IntegralRecord integralRecord = new IntegralRecord();
		integralRecord.init();
		integralRecord.setMember(member);// 积分所属会员
		integralRecord.setIntegralVal(isAddFlag == IntegralRecordEnum.ISADDFLAG_ADD_1 ? Math.abs(integralVal) : -1 * Math.abs(integralVal)); // 积分值
		integralRecord.setIsAddFlag(Integer.valueOf(isAddFlag.getKey()));// 新增或消耗
		integralRecord.setRemark(StringUtils.isNotEmpty(remark) ? (remark.length() <= 200 ? remark : remark.substring(0, 199)) : "");// 备注
		integralRecord.setObjId(objId);// 关联数据主键
		integralRecord.setObjType(Integer.valueOf(objType.getKey()));// 关联数据类型
		integralRecord.setAmount(null == amount ? BigDecimal.ZERO : amount);// *对应面值
		integralRecord.setStatus(Integer.valueOf(IntegralRecordEnum.STATUS_1.getKey())); // 状态默认有效
		integralRecord.setIsPermanent(Integer.valueOf(IntegralRecordEnum.ISPERMANENT_1.getKey()));// 默认永久有效
		integralRecord.setFailureTime(new Date());
		integralRecordRepository.saveAndFlush(integralRecord);
		logger.debug("添加积分明细：{}", integralRecord);
	}
	
	private String buildRemark(String... strings) {
		StringBuilder returnSB = new StringBuilder();
		if (strings.length <= 0) {
			returnSB.append("-");
		}
		for (String string : strings) {
			returnSB.append(string + "-");
		}
		returnSB.replace(returnSB.length() - 1, returnSB.length(), "");
		return returnSB.toString();
	}
	
	public void registerGiveIntegral(Member member, Integer integralVal){
		String remark = this.buildRemark("会员[" + member.getRealCd() + "]手机号[" + member.getPhone() + "]", "于" + member.getRegistTimeStr() + "注册获得积分");
		sendIntegral(member, integralVal, IntegralRecordEnum.ISADDFLAG_ADD_1, remark, member.getMemberId(), IntegralRecordEnum.OBJTYPE_REGISTER_0,
				null);
	}
	
	public void recommendedRegisterGiveIntegral(Member registerMember, Member recommendMember, Integer integralVal) {
		String remark = this.buildRemark(
				"会员[" + recommendMember.getRealCd() + "]["
						+ (StringUtils.isNotEmpty(recommendMember.getRealNm()) ? recommendMember.getRealCd() : recommendMember.getPhone()) + "]",
				"推荐好友[" + registerMember.getRealCd() + "]手机号[" + registerMember.getPhone() + "]", "于" + registerMember.getRegistTimeStr() + "注册获得积分");
		sendIntegral(recommendMember, integralVal, IntegralRecordEnum.ISADDFLAG_ADD_1, remark, registerMember.getMemberId(),
				IntegralRecordEnum.OBJTYPE_RECOMMENDED_FRIEND_REGISTRATION_2, null);
	}
}
