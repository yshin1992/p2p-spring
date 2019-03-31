package org.enums;

/**
 * 配置管理公用明细配置项枚举类
 * @author yanshuai
 *
 */
public enum IntegralRecordEnum {
	/**
	 * 注册 0
	 */
	OBJTYPE_REGISTER_0("0", "注册"),
	/**
	 * 登录 1
	 */
	OBJTYPE_LOGIN_1("1", "登录"),
	/**
	 * 推荐好友注册 2
	 */
	OBJTYPE_RECOMMENDED_FRIEND_REGISTRATION_2("2", "推荐好友注册"),
	/**
	 * 投资成功 3
	 */
	OBJTYPE_INVEST_SUCCESS_3("3", "投资成功"),
	/**
	 * 推荐好友投资成功 4
	 */
	OBJTYPE_RECOMMENDED_FRIEND_INVEST_SUCCESS_4("4", "推荐好友投资成功"),
	/**
	 * 投资额增加 5
	 */
	OBJTYPE_INCREASE_IN_INVESTMENT_5("5", "投资额增加"),
	/**
	 * 投资消耗 6
	 */
	OBJTYPE_INVESTMENT_CONSUMPTION_6("6", "投资消耗"),

	/**
	 * 积分减少
	 */
	ISADDFLAG_SUB_0("0", "支出"),

	/**
	 * 积分增加
	 */
	ISADDFLAG_ADD_1("1", "收入"),
	/**
	 * 无效 0
	 */
	STATUS_0("0","无效"),
	/**
	 * 有效 1
	 */
	STATUS_1("1","有效"),
	
	/**
	 * 有效期根据生效和失效时间判定
	 */
	ISPERMANENT_0("0","有效期内有效"),
	/**
	 * 永久有效
	 */
	ISPERMANENT_1("1","永久有效")
	
	;

	private String key;
	private String text;

	/**
	 * 0注册:增加:关联memberId 1登录:增加:关联memberId 2推荐好友注册:增加:关联memberId 3投资成功:增加:关联投资订单
	 * 4推荐好友投资成功:增加:关联投资订单 5新投资额:增加:关联投资订单 6投资消耗:减少:关联投资订单
	 */
	private IntegralRecordEnum(String key, String text) {
		this.key = key;
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
