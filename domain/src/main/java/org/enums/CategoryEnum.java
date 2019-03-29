package org.enums;

public enum CategoryEnum {
	TRANSFERSETUP("transferSetup","债权转让设置信息"),
	INTEGRALCONFIG("integralConfig","积分设置"),
	BUSINESSTYPE("businessType","业务类型"),
	SITETITLE("siteTitle","网站标题"),
	TRADETYPE("tradeType","交易类型"),
	PLARTFORMVISITLIMIT("plartform.visit.limit","平台会员访问限制");
	
	private String actualval;
	private String attrCd;
	
	private CategoryEnum(String attrCd, String actualval){
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
