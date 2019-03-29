package org.enums;

/**
 * 配置管理公用明细配置项枚举类
 * @author yanshuai
 *
 */
public enum CategoryAttrEnum {
	DAYSOFYEAR("daysOfYear","年对应的天数"),
	FRONTHOMEURL("front.home.url","网站前台首页地址"),
	MAXBROKERLEVEL("maxBrokerLevel","最大经纪人层级"),
	STARTBROKER("startBroker","是否启用经纪人业务"),
	
	
	
	/**
	 * 本系统是否使用积分规则
	 */
	INTEGRAL_ISUSEINTEGRAL("isUseIntegral","本系统是否使用积分规则"),
	/**
	 * 注册获得积分
	 */
	INTEGRAL_REGISTERGIVEINTEGRAL("registerGiveIntegral","注册获得积分"),
	/**
	 * 登录获得积分
	 */
	INTEGRAL_LOGINGIVEINTEGRAL("loginGiveIntegral","登录获得积分"),
	/**
	 * 推荐好友获得积分
	 */
	INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL("recommendFriendsGiveIntegral","推荐好友获得积分"),
	/**
	 * 好友投资获得积分(推荐人)
	 */
	INTEGRAL_FRIENDSINVESTGIVEINTEGRAL("friendsInvestGiveIntegral","好友投资获得积分(推荐人)"),
	/**
	 * 投资获得积分
	 */
	INTEGRAL_INVESTGIVEINTEGRAL("investGiveIntegral","投资获得积分"),
	/**
	 * 用户投资大于过往单次投资金额时
	 */
	INTEGRAL_MAXINVESTGIVEINTEGRAL("maxInvestGiveIntegral","用户投资大于过往单次投资金额时"),
	PLARTFORMVISITLIMITIPMESSAGE("plartform.visit.limit.ip.message","每个IP在1小时内允许发送短信条数"),
	PLARTFORMVISITLIMITPHONEMESSAGE("plartform.visit.limit.phone.message","每手机号在1小时内允许发送短信条数");
	
	
	private String actualval;
	private String attrCd;
	
	
	private CategoryAttrEnum(String attrCd, String actualval){
		this.attrCd = attrCd;
		this.actualval = actualval;
	}
	
	public String getActualval() {
		return actualval;
	}

	public void setActualval(String actualval) {
		this.actualval = actualval;
	}

	public String getAttrCd() {
		return attrCd;
	}

	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}
}
