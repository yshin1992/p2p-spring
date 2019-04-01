package org.vo.sms;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class SmsMemberVo implements Serializable{
	
	private static final long serialVersionUID = -8450296559084682281L;
	
	public SmsMemberVo() {
		super();
	}
	/**
	 * @param realCd 账号
	 * @param realNm 名称
	 */
	public SmsMemberVo(String realCd, String realNm) {
		this(realCd,realCd,null,null);
	}
	/**
	 * @param realCd 账号
	 * @param realNm 名称
	 * @param phone  电话
	 */
	public SmsMemberVo(String realCd, String realNm,String phone) {
		this(realCd,realCd,phone,null);
	}
	/**
	 * @param realCd 账号
	 * @param realNm 名称
	 * @param phone  电话
	 * @param email  邮箱
	 */
	public SmsMemberVo(String realCd, String realNm, String phone, String email) {
		super();
		if(StringUtils.hasLength(realCd)) this.realCd = realCd;
		if(StringUtils.hasLength(realNm)) this.realNm = realNm;
		if(StringUtils.hasLength(phone))  this.phone = phone;
		if(StringUtils.hasLength(email))  this.email = email;
	}
	
	/**检查realCd+realNm、phone、email 参数至少有一个不为空*/
	public boolean isCheck(){
		if(StringUtils.hasLength(realCd)&&StringUtils.hasLength(realNm)) return true;
		if(StringUtils.hasLength(phone))  return true;
		if(StringUtils.hasLength(email))  return true;
		return false;
	}
	// 会员登录账号 
	private String realCd;
	
	// 实际姓名
	private String realNm;
	
	
	// 验证手机
	private String phone;
	
	// 验证邮箱
	private String email;

	/** 会员登录账号 */
	public String getRealCd() {
		return realCd;
	}
	/** 会员登录账号 */
	public void setRealCd(String realCd) {
		this.realCd = realCd;
	}
	/**实际姓名*/
	public String getRealNm() {
		return realNm;
	}
	/**实际姓名*/
	public void setRealNm(String realNm) {
		this.realNm = realNm;
	}
	/**验证手机*/
	public String getPhone() {
		return phone;
	}
	/**验证手机*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**验证邮箱*/
	public String getEmail() {
		return email;
	}
	/**验证邮箱*/
	public void setEmail(String email) {
		this.email = email;
	}
	
}
