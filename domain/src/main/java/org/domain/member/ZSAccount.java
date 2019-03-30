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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 浙商账户信息实体表
 * @author yanshuai
 *
 */
@Entity
@Table(name="zsaccount")
public class ZSAccount extends DynamicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2156287611460525400L;

	public static final Integer BUSINESS_PROPERTY_INVESTOR=1;//投资人
	public static final Integer BUSINESS_PROPERTY_BORROWER=2;//借款人
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String accountId;
	
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name = "memberId")
	private Member member;
	
	/**
	 * 开户的手机号
	 */
	@Column(length = 13)
	private String openTel;

	/**
	 * 开户时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openTime = new Date();

	/**
	 * 绑定协议号
	 */
	@Column(length = 30)
	private String bindSerialNo;

	/**
	 * 存管账号
	 */
	@Column(length = 20)
	private String ecardNo;

	/**
	 * 存管账户类型
	 * 0存管e户  1商卡
	 */
	@Column(length = 2)
	private String type;

	/**
	 * 证件类型
	 * 00 其他 01 身份证 02 护照 03 军官证 04 组织机构代码号
	 */
	@Column
	private String certType;

	/**
	 * 证件号码
	 */
	@Column
	private String certNo;

	/**
	 * 主账号
	 */
	@Column
	private String openBankCard;

	/**
	 * 人行联行行号
	 */
	@Column
	private String unionNumber;

	/**
	 * 用户平台编码
	 */
	@Column
	private String platformUid;

	/**
	 * 其他平台账户保留前六后四，中间以*代替
	 */
	@Column
	private String otherAccno;

	@Column
	private String bankName;
	/**
	 * 主账户他行人行联行行号
	 */
	@Column
	private String branchNo;

	/**
	 * 0失败1成功
	 */
	@Column
	private Integer updateStatus;

	/**
	 * 变更失败原因
	 */
	@Column
	private String failReason;

	/**
	 * 默认为”空”是直销银行变更，1为平台变更
	 */
	@Column
	private String extension;
	
	/**
	 * 客户类型 1 投资户 2 融资户
	 * 默认为投资户
	 */
	@Column
	private Integer businessProperty=1;
	
	@Override
	public void setId(String id) {
		this.accountId = id;
	}

	@Override
	public String getId() {
		return this.accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getOpenTel() {
		return openTel;
	}

	public void setOpenTel(String openTel) {
		this.openTel = openTel;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getBindSerialNo() {
		return bindSerialNo;
	}

	public void setBindSerialNo(String bindSerialNo) {
		this.bindSerialNo = bindSerialNo;
	}

	public String getEcardNo() {
		return ecardNo;
	}

	public void setEcardNo(String ecardNo) {
		this.ecardNo = ecardNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getOpenBankCard() {
		return openBankCard;
	}

	public void setOpenBankCard(String openBankCard) {
		this.openBankCard = openBankCard;
	}

	public String getUnionNumber() {
		return unionNumber;
	}

	public void setUnionNumber(String unionNumber) {
		this.unionNumber = unionNumber;
	}

	public String getPlatformUid() {
		return platformUid;
	}

	public void setPlatformUid(String platformUid) {
		this.platformUid = platformUid;
	}

	public String getOtherAccno() {
		return otherAccno;
	}

	public void setOtherAccno(String otherAccno) {
		this.otherAccno = otherAccno;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public Integer getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Integer getBusinessProperty() {
		return businessProperty;
	}

	public void setBusinessProperty(Integer businessProperty) {
		this.businessProperty = businessProperty;
	}

	public String getCertTypeX() {
		String type = "";
		if("00".equals(certType)){
			type = "其他";
		}else if("01".equals(certType)){
			type = "身份证";
		}else if("02".equals(certType)){
			type = "护照";
		}else if("03".equals(certType)){
			type = "军官证";
		}else if("04".equals(certType)){
			type = "组织机构代码号";
		}else if("05".equals(certType)){
			type = "三证号";
		}
		return type;
	}
	
	public String getExtension() {
		if(null == this.extension){
			return null;
		}else if("".equals(this.extension)){
			return "直销银行变更";
		}else if("1".equals(this.extension)){
			return "平台变更";
		}
		return extension;
	}

	@Override
	public String toString() {
		return "ZSAccount [accountId=" + accountId + ", openTel=" + openTel
				+ ", openTime=" + openTime + ", bindSerialNo=" + bindSerialNo
				+ ", ecardNo=" + ecardNo + ", type=" + type + ", certType="
				+ certType + ", certNo=" + certNo + ", openBankCard="
				+ openBankCard + ", unionNumber=" + unionNumber
				+ ", platformUid=" + platformUid + ", otherAccno=" + otherAccno
				+ ", bankName=" + bankName + ", branchNo=" + branchNo
				+ ", updateStatus=" + updateStatus + ", failReason="
				+ failReason + ", extension=" + extension
				+ ", businessProperty=" + businessProperty + "]";
	}
	
	
}
