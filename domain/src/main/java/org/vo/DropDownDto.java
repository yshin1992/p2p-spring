package org.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DropDownDto implements Serializable {
	private static final long serialVersionUID = 6101258681959185966L;
	private String attrCd;
	private String attrNm;
	private boolean display = true;

	private String actualval;

	private String defaultVal;

	public DropDownDto() {
	}

	public DropDownDto(String attrCd, String attrNm) {
		this(attrCd, attrNm, true, null, null);
	}

	public String toString() {
		return "DropDownDto [getStatus()=" + getStatus() + ", getPay()="
				+ getPay() + ", isDisplay()=" + isDisplay() + ", getAttrCd()="
				+ getAttrCd() + ", getAttrNm()=" + getAttrNm()
				+ ", getActualval()=" + getActualval() + ", getDefaultVal()="
				+ getDefaultVal() + "]";
	}

	public DropDownDto(String attrCd, String attrNm, boolean display) {
		this(attrCd, attrNm, display, null, null);
	}

	public DropDownDto(String attrCd, String attrNm, boolean display,
			String actualval, String defaultVal) {
		this.attrCd = attrCd;
		this.attrNm = attrNm;
		this.display = display;
		this.actualval = actualval;
		this.defaultVal = defaultVal;
	}

	public String getStatus() {
		return getAttrCd();
	}

	public void setStatus(String status) {
		setAttrCd(status);
	}

	public String getPay() {
		return getAttrNm();
	}

	public void setPay(String pay) {
		setAttrNm(pay);
	}

	public boolean isDisplay() {
		return this.display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	@JsonIgnore
	public String getAttrCd() {
		return this.attrCd;
	}

	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}

	@JsonIgnore
	public String getAttrNm() {
		return this.attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getActualval() {
		return this.actualval;
	}

	public void setActualval(String actualval) {
		this.actualval = actualval;
	}

	public String getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
}
