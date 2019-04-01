package org.domain.member;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class MemberIntegral extends DynamicEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6923213203516815353L;

	/**
	 * 会员标识
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(length = 32)
	private String intergralId;

	/**
	 * 会员
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="memberId")
	private Member member;

	/**
	 * 可用积分
	 */
	@Column
	private Integer integralVal;
	/**
	 * 获取积分总额
	 */
	@Column
	private Integer total;
	/**
	 * 已使用积分
	 */
	@Column
	private Integer usedValue;
	/**
	 * 已使用面值
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal usedAmount;
	/**
	 * 最高投资记录
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal maxInvestAmount;

	@Override
	public String getId() {
		return intergralId;
	}

	@Override
	public void setId(String id) {
		this.intergralId = id;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getUsedValue() {
		return usedValue;
	}

	public void setUsedValue(Integer usedValue) {
		this.usedValue = usedValue;
	}

	public BigDecimal getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}

	public BigDecimal getMaxInvestAmount() {
		return maxInvestAmount;
	}

	public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
		this.maxInvestAmount = maxInvestAmount;
	}

	public String getUpdateTimeStr() {
		if (null == getUpdateTime())
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getUpdateTime());
	}

	public String getIntergralId() {
		return intergralId;
	}

	public void setIntergralId(String intergralId) {
		this.intergralId = intergralId;
	}

	@Override
	public String toString() {
		return "MemberIntegral [intergralId=" + intergralId + ", member="
				+ member + ", integralVal=" + integralVal + ", total=" + total
				+ ", usedValue=" + usedValue + ", usedAmount=" + usedAmount
				+ ", maxInvestAmount=" + maxInvestAmount + "]";
	}

}
