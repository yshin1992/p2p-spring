package org.vo;

public class IntegralSetDto {

	// 本系统是否使用积分规则
	private Integer isUseIntegral;
	// 注册获得积分
	private Integer registerGiveIntegral;
	// 登录获得积分
	private Integer loginGiveIntegral;
	// 推荐好友获得积分
	private Integer recommendFriendsGiveIntegral;
	// 好友投资获得积分(推荐人)
	private Integer friendsInvestGiveIntegral;
	// 投资获得积分
	private Integer investGiveIntegral;
	// 用户投资大于过往单次投资金额时
	private Integer maxInvestGiveIntegral;
	public Integer getIsUseIntegral() {
		return isUseIntegral;
	}
	public void setIsUseIntegral(Integer isUseIntegral) {
		this.isUseIntegral = isUseIntegral;
	}
	public Integer getRegisterGiveIntegral() {
		return registerGiveIntegral;
	}
	public void setRegisterGiveIntegral(Integer registerGiveIntegral) {
		this.registerGiveIntegral = registerGiveIntegral;
	}
	public Integer getLoginGiveIntegral() {
		return loginGiveIntegral;
	}
	public void setLoginGiveIntegral(Integer loginGiveIntegral) {
		this.loginGiveIntegral = loginGiveIntegral;
	}
	public Integer getRecommendFriendsGiveIntegral() {
		return recommendFriendsGiveIntegral;
	}
	public void setRecommendFriendsGiveIntegral(Integer recommendFriendsGiveIntegral) {
		this.recommendFriendsGiveIntegral = recommendFriendsGiveIntegral;
	}
	public Integer getFriendsInvestGiveIntegral() {
		return friendsInvestGiveIntegral;
	}
	public void setFriendsInvestGiveIntegral(Integer friendsInvestGiveIntegral) {
		this.friendsInvestGiveIntegral = friendsInvestGiveIntegral;
	}
	public Integer getInvestGiveIntegral() {
		return investGiveIntegral;
	}
	public void setInvestGiveIntegral(Integer investGiveIntegral) {
		this.investGiveIntegral = investGiveIntegral;
	}
	public Integer getMaxInvestGiveIntegral() {
		return maxInvestGiveIntegral;
	}
	public void setMaxInvestGiveIntegral(Integer maxInvestGiveIntegral) {
		this.maxInvestGiveIntegral = maxInvestGiveIntegral;
	}

	
	
}
