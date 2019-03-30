package org.domain.member;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 会员银行卡信息
 * @author yanshuai
 *
 */
@Entity
public class BankCard extends DynamicEntity {

	private static final long serialVersionUID = -6806391309088214360L;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String bankCardId;// 银行卡表示ID

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "memberId")
	private Member member;

	@Column(length = 20)
	private String bankNo; // 银行卡号

	@Column()
	private Integer bankCardType;// 银行卡类型

	@Column(length = 20)
	private String idCard; // 开户证件号

	@Column(length = 11)
	private String bankTelephone; // 开户手机号

	@Column(length = 128)
	private String bankType; // 所在银行

	@Column(length = 128)
	private String bankBranch; // 开户支行

	@Column(length = 128)
	private String bankAddress; // 开户所在地

	@Column(length = 32)
	private String bankCd; // 银行代码

	@Column(length = 32)
	private String province; // 开户行省份

	@Column(length = 32)
	private String city; // 开户行城市

	@Column(length = 32)
	private String area; // 开户行区

	@Column(length = 32)
	private String provinceNm; // 开户行省份名称

	@Column(length = 32)
	private String cityNm; // 开户行城市名称

	@Column(length = 32)
	private String areaNm; // 开户行区名称

	@Column(length = 32)
	private String eAccountNo;// 电子账户号

	@Transient
	private String realNm;

	// 提交开始时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date submitTimeFrom;

	// 提交结束时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date submitTimeTo;

	/**
	 * 银行协议号
	 */
	@Column(length = 32)
	private String protocolNo;

	/**
	 * 是否默认银行卡
	 */
	@Column
	private Boolean defaultCard;
	
	@Column(length = 32)
	private String trxId;//交易流水号
	@Column(length = 10)
	private String statusCode;//状态代码
	@Column(length = 32)
	private String statusDesc;//状态描述

	public Boolean getDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(Boolean defaultCard) {
		this.defaultCard = defaultCard;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public Integer getBankCardType() {
		return bankCardType;
	}

	public void setBankCardType(Integer bankCardType) {
		this.bankCardType = bankCardType;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String geteAccountNo() {
		return eAccountNo;
	}

	public void seteAccountNo(String eAccountNo) {
		this.eAccountNo = eAccountNo;
	}

	public String getBankTelephone() {
		return bankTelephone;
	}

	public void setBankTelephone(String bankTelephone) {
		this.bankTelephone = bankTelephone;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankCd() {
		return bankCd;
	}

	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRealNm() {
		return realNm;
	}

	public void setRealNm(String realNm) {
		this.realNm = realNm;
	}

	public Date getSubmitTimeFrom() {
		return submitTimeFrom;
	}

	public void setSubmitTimeFrom(Date submitTimeFrom) {
		this.submitTimeFrom = submitTimeFrom;
	}

	public Date getSubmitTimeTo() {
		return submitTimeTo;
	}

	public void setSubmitTimeTo(Date submitTimeTo) {
		this.submitTimeTo = submitTimeTo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvinceNm() {
		return provinceNm;
	}

	public void setProvinceNm(String provinceNm) {
		this.provinceNm = provinceNm;
	}

	public String getCityNm() {
		return cityNm;
	}

	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	public String getAreaNm() {
		return areaNm;
	}

	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}

	@Override
	public String toString() {
		return "BankCard [bankCardId=" + bankCardId + ", memberId=" + member.getMemberId() + ", bankNo=" + bankNo + ", bankCardType=" + bankCardType + ", idCard=" + idCard + ", bankTelephone=" + bankTelephone + ", bankType=" + bankType + ", bankBranch=" + bankBranch + ", bankAddress=" + bankAddress + ", bankCd="
				+ bankCd + ", province=" + province + ", city=" + city + ", area=" + area + ", provinceNm=" + provinceNm + ", cityNm=" + cityNm + ", areaNm=" + areaNm + ", eAccountNo=" + eAccountNo + ", realNm=" + realNm + ", submitTimeFrom=" + submitTimeFrom + ", submitTimeTo=" + submitTimeTo
				+ ", protocolNo=" + protocolNo + ", defaultCard=" + defaultCard + ", trxId=" + trxId + ", statusCode=" + statusCode + ", statusDesc=" + statusDesc + ", getUpdateTime()=" + getUpdateTime() + ", getUpdateBy()=" + getUpdateBy() + ", toString()=" + super.toString() + ", getDeleted()="
				+ getDeleted() + ", getEffTime()=" + getEffTime() + ", getExpTime()=" + getExpTime() + ", getCreateTime()=" + getCreateTime() + ", getCreateBy()=" + getCreateBy() + ", getCreateTimeStr()=" + getCreateTimeStr() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	@Override
	public String getId() {
		return this.getBankCardId();
	}

	@Override
	public void setId(String id) {
		this.setBankCardId(id);
		;
	}

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
