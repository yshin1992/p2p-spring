package org.domain.member;

import java.math.BigDecimal;
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

import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 积分记录表实体 用于保存会员的积分增减记录
 * @author yanshuai
 *
 */
@Entity
@Table
public class IntegralRecord extends DynamicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 106173038085154500L;

	/**
	 * 记录标识
	 */
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String recordId;//

	/**
	 * 会员标识
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "memberId")
	private Member member;

	/**
	 * 积分值 用于页面显示，便于查询
	 */
	@Column
	private Integer integralVal;
	/**
	 * 增减标志 1增加 0减少
	 */
	@Column
	private Integer isAddFlag;

	/**
	 * 状态 0未生效/失效 1有效
	 */
	@Column
	private Integer status;

	/**
	 * 说明
	 */
	@Column(length = 200)
	private String remark;

	/**
	 * 失效时间
	 */
	@Column
	private Date failureTime;

	/**
	 * 是否永久有效 0不是 1永久有效
	 */
	@Column
	private Integer isPermanent;

	/**
	 * 0注册:增加:关联memberId 1登录:增加:关联memberId 2推荐好友注册:增加:关联memberId 3投资成功:增加:关联投资订单
	 * 4推荐好友投资成功:增加:关联投资订单 5新投资额:增加:关联投资订单 6投资消耗:减少:关联投资订单
	 */
	@Column
	private Integer objType;
	/**
	 * 关联数据主键 一般是memberId或者orderId
	 */
	@Column(length = 32)
	private String objId;

	/**
	 * 对应面值 不是消耗积分的场合,可空
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amount;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getIntegralVal() {
		return integralVal;
	}

	public void setIntegralVal(Integer integralVal) {
		this.integralVal = integralVal;
	}

	public Integer getIsAddFlag() {
		return isAddFlag;
	}

	public void setIsAddFlag(Integer isAddFlag) {
		this.isAddFlag = isAddFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

	public Integer getIsPermanent() {
		return isPermanent;
	}

	public void setIsPermanent(Integer isPermanent) {
		this.isPermanent = isPermanent;
	}

	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String getId() {
		return getRecordId();
	}

	@Override
	public void setId(String id) {
		this.setRecordId(id);
	}

	@Override
	public String toString() {
		return "IntegralRecord [recordId=" + recordId + ", memberId=" + member.getMemberId() + ", integralVal=" + integralVal
				+ ", isAddFlag=" + isAddFlag + ", status=" + status + ", remark=" + remark + ", failureTime="
				+ failureTime + ", isPermanent=" + isPermanent + ", objType=" + objType + ", objId=" + objId
				+ ", amount=" + amount + "]";
	}

}
