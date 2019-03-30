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
import javax.persistence.Transient;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 会员审核认证实体类
 * @author yanshuai
 *
 */
@Table(name = "member_audit")
@Entity
public class Audit extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 实名认证
	 */
	public static final Integer ID_AUDIT = 1;

	/**
	 * 银行卡认证
	 */
	public static final Integer BANKCARD_AUDIT = 2;

	// 0：待审核，1：认证成功，2：认证失败
	/**
	 * 待审核
	 */
	public static final Integer STATUS_ONAUDIT = 0;

	/**
	 * 审核成功
	 */
	public static final Integer STATUS_AUDIT_SUCCESS = 1;

	/**
	 * 审核失败
	 */
	public static final Integer STATUS_AUDIT_FAILED = 2;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String auditId;// 审核ID

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId")
	private Member member;

	@Column(length = 13)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date submitTime;// 提交时间

	@Column(length = 13)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date auditTime;// 审核时间

	@Transient
	private String realNm;

	@Column()
	private Integer auditType;// 1 ：身份证认证(实名认证) 2 ：银行卡认证 4 : 邮箱验证 8 : 手机验证 16:
								// 32: ... 其他认证再进行扩展
	/**
	 * 1 ：是否身份证认证(实名认证) 
	 * @return
	 */
	@Transient
	public boolean getAuditType1(){
		return (auditType & 1) ==1;
	}
	/**
	 * 2 ：是否银行卡认证
	 * @return
	 */
	@Transient
	public boolean getAuditType2(){
		return (auditType & 2) ==2;
	}
	/**
	 *  4 : 是否邮箱验证
	 * @return
	 */
	@Transient
	public boolean getAuditType4(){
		return (auditType & 4) ==4;
	}
	/**
	 * 8 : 是否手机验证
	 * @return
	 */
	@Transient
	public boolean getAuditType8(){
		return (auditType & 8) ==8;
	}
	@Column()
	private Integer status;// 0：待审核，1：认证成功，2：认证失败

	@Column(length = 32)
	private String auditAuthor;// 审核人账号

	@Column(length = 256)
	private String auditDesc;// 备注

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

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAuditAuthor() {
		return auditAuthor;
	}

	public void setAuditAuthor(String auditAuthor) {
		this.auditAuthor = auditAuthor;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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

	public String getRealNm() {
		return realNm;
	}

	public void setRealNm(String realNm) {
		this.realNm = realNm;
	}

	@Override
	public String toString() {
		return "Audit[auditId=" + auditId + ",submitTime=" + submitTime
				+ ",auditTime=" + auditTime + ",auditType=" + auditType
				+ ",status=" + status + ",auditAuthor=" + auditAuthor
				+ ",auditDesc=" + auditDesc + "]";
	}

	@Override
	public String getId() {
		return this.getAuditId();
	}

	@Override
	public void setId(String id) {
		this.setAuditId(id);

	}

}
