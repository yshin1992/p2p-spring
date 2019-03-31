package org.apis.service.member;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.apis.dao.member.MemberIntegralRepository;
import org.apis.dao.member.MemberRepository;
import org.apis.service.CacheUtil;
import org.domain.member.Member;
import org.domain.member.MemberIntegral;
import org.enums.CategoryAttrEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分服务接口
 * @author yanshuai
 *
 */
@Service
public class MemberIntegralService {

	private static final Logger logger = LoggerFactory.getLogger(MemberIntegralService.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberIntegralRepository memberIntegralRepository;
	
	@Autowired
	private IntegralRecordService integralRecordService;
	
	/**
	 * 初始化会员积分信息
	 * @param memberId
	 * @return
	 */
	public MemberIntegral initMemberIntegral(String memberId) {
		MemberIntegral mi = memberIntegralRepository.findOne(memberId);
		if(null == mi){
			Member member = memberRepository.findOne(memberId);
			if(null == member){
				throw new RuntimeException("未查到会员信息");
			}
			mi = new MemberIntegral();
			mi.init();
			mi.setCreateTime(member.getRegistTime());
			mi.setMember(member);
			mi.setTotal(0);
			mi.setMaxInvestAmount(BigDecimal.ZERO);
			mi.setIntegralVal(0);
			mi.setUsedAmount(BigDecimal.ZERO);
			mi.setUsedValue(0);
			mi = memberIntegralRepository.saveAndFlush(mi);
		}
		return mi; 
	}
	
	private boolean checkIsUseIntegral(){
		Integer isUseIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd(), 0);
		if(isUseIntegral<=0){
			logger.error("本系统未启用积分相关业务。是否启用积分业务配置参数值：{}", isUseIntegral);
			return false;
		}
		return true;
	}
	
	/**
	 * 注册赠送积分
	 * @param memberId
	 */
	public void registerGiveIntegral(String memberId){
		if(!checkIsUseIntegral()){
			return;
		}
		logger.debug("注册获得积分=====传入参数：memberId:{}", memberId);
		Member registMember = memberRepository.findOne(memberId);
		if(null == registMember){
			throw new RuntimeException("未查询到注册会员信息");
		}
		MemberIntegral mi = memberIntegralRepository.findOne(memberId);
		if(null == mi){
			throw new RuntimeException("未查询到会员积分信息");
		}
		// 获取积分配置参数
		Integer registerIntegralVal = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd(), 0);
		if (registerIntegralVal <= 0) {
			logger.debug("注册未获得积分。积分设计中注册获得积分送参数值为0。integralVal:{}", registerIntegralVal);
			return;
		}
		
		// 添加注册会员的获得积分记录
		// 添加明细
		integralRecordService.registerGiveIntegral(registMember, registerIntegralVal);
		// 更新会员积分总数据
		mi.setIntegralVal(mi.getIntegralVal()+registerIntegralVal);
		memberIntegralRepository.saveAndFlush(mi);
		logger.debug("注册获得积分=====更新注册会员积分=====获得积分:{}分", registerIntegralVal);
		
		// 添加注册会员的推荐人获得积分记录
		// 推荐人获得积分
		if(StringUtils.isNotEmpty(registMember.getMemberIdZ())){
			Member memberZ = memberRepository.findOne(registMember.getMemberIdZ());
			if(null == memberZ){
				throw new RuntimeException("未查到推荐人信息");
			}
			MemberIntegral miZ = memberIntegralRepository.findOne(registMember.getMemberIdZ());
			if(null == miZ){
				throw new RuntimeException("未查询到推荐人积分信息");
			}
			// 投资会员的推荐人获得投资积分系统配置
			Integer recommendFriendsGiveIntegral = CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd(), 0);
			if(recommendFriendsGiveIntegral > 0){
				// 添加积分明细
				integralRecordService.recommendedRegisterGiveIntegral(registMember, memberZ, recommendFriendsGiveIntegral);
				// 更新积分总数
				miZ.setIntegralVal(miZ.getIntegralVal() + recommendFriendsGiveIntegral);
				memberIntegralRepository.saveAndFlush(miZ);
				logger.debug("注册获得积分=====更新注册会员的推荐人积分=====获得积分:{}分", recommendFriendsGiveIntegral);
			}
		}
		
	}
	
}
