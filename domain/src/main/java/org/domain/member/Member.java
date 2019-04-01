package org.domain.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 会员实体类信息
 * @author yanshuai
 *
 */
@Entity
@Table(name="member")
public class Member extends DynamicEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Integer MEMBER_KIND_PERSONAL = 0;
	public static final Integer MEMBER_KIND_ENTERPRISE = 1;
	/***
	 * 0：限制登录
	 */
	public static final Integer MEMBER_STATUS_LIMIT = 0;
	/***
	 * 1：正常状态
	 */
	public static final Integer MEMBER_STATUS_NORMAL = 1;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String memberId;// 会员ID
	
	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Audit> audits = new HashSet<Audit>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<BankCard> bankCards = new HashSet<BankCard>();

	@JsonIgnore
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<Account>();
	
	@JsonIgnore
	@OneToMany(mappedBy="member",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private Set<ZSAccount> zsAccounts = new HashSet<ZSAccount>();
	
	@Column()
	private Integer memberKind;// 客户类型0:个人，1:企业

	@Column(length = 32)
	private String realCd;// 会员登录账号 规则:md5(md5(password)+realCd)

	@Column(length = 64)
	private String realNm;// 实际姓名

	@Column(length = 64)
	private String nickName;// 昵称

	@Column(length = 32)
	private String password;// 密码

	@Column(length = 18)
	private String idCard;// 身份证号码

	@Column(length = 20)
	private String bankNo;// 验证银行卡

	@Column(length = 13)
	private String phone;// 验证手机

	@Column(length = 64)
	private String email;// 验证邮箱
	
	@Column(length = 256)
	private String address;//住址

	@Column()
	private Integer level;// 会员等级1：普通会员，2：至尊会员（根据需要进行扩张）

	@Column(length = 13)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registTime;// 注册时间

	@Column()
	private Integer status;// 状态1：正常状态，0：限制登录
	
	@Column(length = 13)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLogin;// 最近登录时间

	@Column()
	private Integer loginCount;// 登录次数

	// 注册开始时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date registTimeFrom;

	// 注册结束时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date registTimeTo;

	// 登录开始时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date lastLoginFrom;

	// 登录结束时间
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Transient
	private Date lastLoginTo;

	/**
	 * 最后登录ip
	 */
	@Column(length = 32)
	private String lastLoginIp;
	/*
	 * //认证方式1 ：身份证认证(实名认证) 2 ：银行卡认证 4 : 邮箱验证 8 : 手机验证 16 授权 32 浙商开户
	 * 当存在多种验证通过时把认证类型通过直接以整数的形式加起来即可 比如：1+2=3 表示身份认证审核通过、银行卡认证通过
	 */
	@Column(length = 13)
	private Integer auditType = 0;
	
	
	/**
	 * 1 ：是否身份证认证(实名认证)
	 *
	 * @return
	 */
	@Transient
	public boolean getAuditType1() {
		return (auditType & 1) == 1;
	}

	/**
	 * 1 ：是否身份证认证(实名认证) 用于页面显示
	 *
	 * @return
	 */
	@Transient
	public String getAuditType1x() {
		if ((auditType & 1) == 1) {
			realNm = StringUtils.isNotEmpty(realNm) ? realNm : "";
			idCard = StringUtils.isNotEmpty(idCard) ? "(" + idCard + ")" : "";
			return "已认证   " + realNm + idCard;
		}
		return "未认证";
	}

	/**
	 * 2 ：是否银行卡认证
	 *
	 * @return
	 */
	@Transient
	public boolean getAuditType2() {
		return (auditType & 2) == 2;
	}

	/**
	 * 2 ：是否银行卡认证 用于页面显示
	 *
	 * @return
	 */
	@Transient
	public String getAuditType2x() {
		if ((auditType & 2) == 2)
			return "已认证   " + (StringUtils.isNotEmpty(bankNo) ? bankNo : "");
		return "未认证";
	}

	/**
	 * 4 : 是否邮箱验证
	 *
	 * @return
	 */
	@Transient
	public boolean getAuditType4() {
		return (auditType & 4) == 4;
	}

	/**
	 * 8 : 是否手机验证
	 *
	 * @return
	 */
	@Transient
	public boolean getAuditType8() {
		return (auditType & 8) == 8;
	}

	/**
	 * 16：是否授权
	 *
	 * @return
	 */
	@Transient
	public boolean getAuditType16() {
		return (auditType & 16) == 16;
	}

	/**
	 * 8 ：是否手机验证 用于页面显示
	 *
	 * @return
	 */
	@Transient
	public String getAuditType8x() {
		if ((auditType & 8) == 8)
			return "已认证   " + (StringUtils.isNotEmpty(phone) ? phone : "");
		return "未认证";
	}

	@Transient
	public boolean getAuditType32(){
		return (auditType & 32) == 32;
	}

	@Transient
	public String getAuditType32x() {
		if ((auditType & 32) == 32)
			return "已开户   " + (StringUtils.isNotEmpty(phone) ? phone : "");
		return "未开户";
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(length = 12)
	private String promotionId;// 推广id

	@Column(length = 32)
	private String memberIdZ;// 推荐人id
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy="member", fetch = FetchType.LAZY, optional = true)
	private MemberIntegral memberIntegral;
	
	@Override
	public void setId(String id) {
		this.memberId = id;
	}

	@Override
	public String getId() {
		return this.memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * 客户类型0:个人，1:企业
	 * @return
	 */
	@Transient
	public String getMemberKindNm() {
		switch (memberKind) {
		case 1:
			return "企业";
		case 11:
			return "网贷公司";
		case 12:
			return "担保公司";
		case 13:
			return "第三方支付公司";
		default:
			return "";
		}
	}
	
	@Transient
	public String getLevelName() {
		switch (level) {
		case 1:
			return "普通会员";
		case 2:
			return "至尊会员";
		default:
			return "";
		}
	}
	
	@Transient
	public String getRegistTimeStr() {
		if (null == getRegistTime())
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd").format(getRegistTime());
	}
	
	public String getLastLoginFormat() {
		if (lastLogin != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastLogin);
		} else {
			return "";
		}
	}

	public String getRegistFormat() {
		if (lastLogin != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registTime);
		} else {
			return "";
		}
	}

	public Set<Audit> getAudits() {
		return audits;
	}

	public void setAudits(Set<Audit> audits) {
		this.audits = audits;
	}

	public Set<BankCard> getBankCards() {
		return bankCards;
	}

	public void setBankCards(Set<BankCard> bankCards) {
		this.bankCards = bankCards;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Integer getMemberKind() {
		return memberKind;
	}

	public void setMemberKind(Integer memberKind) {
		this.memberKind = memberKind;
	}

	public String getRealCd() {
		return realCd;
	}

	public void setRealCd(String realCd) {
		this.realCd = realCd;
	}

	public String getRealNm() {
		return realNm;
	}

	public void setRealNm(String realNm) {
		this.realNm = realNm;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getRegistTimeFrom() {
		return registTimeFrom;
	}

	public void setRegistTimeFrom(Date registTimeFrom) {
		this.registTimeFrom = registTimeFrom;
	}

	public Date getRegistTimeTo() {
		return registTimeTo;
	}

	public void setRegistTimeTo(Date registTimeTo) {
		this.registTimeTo = registTimeTo;
	}

	public Date getLastLoginFrom() {
		return lastLoginFrom;
	}

	public void setLastLoginFrom(Date lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
	}

	public Date getLastLoginTo() {
		return lastLoginTo;
	}

	public void setLastLoginTo(Date lastLoginTo) {
		this.lastLoginTo = lastLoginTo;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getMemberIdZ() {
		return memberIdZ;
	}

	public void setMemberIdZ(String memberIdZ) {
		this.memberIdZ = memberIdZ;
	}

	public MemberIntegral getMemberIntegral() {
		return memberIntegral;
	}

	public void setMemberIntegral(MemberIntegral memberIntegral) {
		this.memberIntegral = memberIntegral;
	}

	public Set<ZSAccount> getZsAccounts() {
		return zsAccounts;
	}

	public void setZsAccounts(Set<ZSAccount> zsAccounts) {
		this.zsAccounts = zsAccounts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", audits=" + audits
				+ ", bankCards=" + bankCards + ", accounts=" + accounts
				+ ", zsAccounts=" + zsAccounts + ", memberKind=" + memberKind
				+ ", realCd=" + realCd + ", realNm=" + realNm + ", nickName="
				+ nickName + ", password=" + password + ", idCard=" + idCard
				+ ", bankNo=" + bankNo + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", level=" + level
				+ ", registTime=" + registTime + ", status=" + status
				+ ", lastLogin=" + lastLogin + ", loginCount=" + loginCount
				+ ", registTimeFrom=" + registTimeFrom + ", registTimeTo="
				+ registTimeTo + ", lastLoginFrom=" + lastLoginFrom
				+ ", lastLoginTo=" + lastLoginTo + ", lastLoginIp="
				+ lastLoginIp + ", auditType=" + auditType + ", promotionId="
				+ promotionId + ", memberIdZ=" + memberIdZ
				+ ", memberIntegral=" + memberIntegral + "]";
	}
	

}
