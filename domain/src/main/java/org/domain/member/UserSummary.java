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

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user_summary")
public class UserSummary extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String id;
	/**
	 * 会员标识
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "memberId")
	private Member member;
	/**
	 * 奖励总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountAward = BigDecimal.ZERO;
	/**
	 * 投资奖励
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountAwardInvest = BigDecimal.ZERO;
	
	/**
	 * 投资积分（现用于红包功能）
	 * 投资后按金额累计积分，红包提现后减少
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal investIntegral = BigDecimal.ZERO;
	/**
	 * 投资奖励笔数
	 */
	@Column
	private Integer amountAwardRow = 0;
	/**
	 * 提现总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountCash = BigDecimal.ZERO;
	/**
	 * 累计提现笔数
	 */
	@Column
	private Integer amountCashRow = 0;
	/**
	 * 待还总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayAll = BigDecimal.ZERO;
	/**
	 * 待还本金
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayCapital = BigDecimal.ZERO;
	/**
	 * 待还利息
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayInterest = BigDecimal.ZERO;
	/**
	 * 累计待还笔数
	 */
	@Column
	private Integer amountDueRepayRow = 0;
	/**
	 * 已还总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayedAll = BigDecimal.ZERO;
	/**
	 * 已还本金
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayedCapital = BigDecimal.ZERO;
	/**
	 * 已还利息
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueRepayedInterest = BigDecimal.ZERO;
	/**
	 * 累计已还笔数
	 */
	@Column
	private Integer amountDueRepayedRow = 0;
	/**
	 * 待收总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueinAll = BigDecimal.ZERO;
	/**
	 * 待收本金
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueinCapital = BigDecimal.ZERO;
	/**
	 * 待收利息
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountDueinInterest = BigDecimal.ZERO;
	/**
	 * 待收笔数
	 */
	@Column
	private Integer amountDueinRow = 0;
	/**
	 * 已收总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInedAll = BigDecimal.ZERO;
	/**
	 * 已收本金
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInedCapital = BigDecimal.ZERO;
	/**
	 * 已收利息
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInedInterest = BigDecimal.ZERO;
	/**
	 * 累计已收笔数
	 */
	@Column
	private Integer amountInedRow = 0;
	/**
	 * 累计投资金额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInvest = BigDecimal.ZERO;
	/**
	 * 累计投资笔数
	 */
	@Column
	private Integer amountInvestRow = 0;
	/**
	 * 充值总额
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountRecharge = BigDecimal.ZERO;
	/**
	 * 累计充值笔数
	 */
	@Column
	private Integer amountRechargeRow = 0;
	/**
	 * 邀请奖励
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInvit = BigDecimal.ZERO;
	/**
	 * 利息管理费汇总
	 */
	@Column(precision = 19, scale = 2)
	private BigDecimal amountInterestFee = BigDecimal.ZERO;

	public UserSummary() {
	}
	
	/**
	 * 初始化用构造体
	 * <br>用于用户注册时，填充初始化会员投资借款数据
	 * 
	 * @param saveMember 注册时被保存的会员
	 */
	public UserSummary(Member saveMember) {
		this.setMember(saveMember);
		this.setAmountAward(BigDecimal.ZERO);
		this.setAmountAwardInvest(BigDecimal.ZERO);
		this.setAmountAwardRow(0);
		this.setAmountCash(BigDecimal.ZERO);
		this.setAmountCashRow(0);
		this.setAmountDueinAll(BigDecimal.ZERO);
		this.setAmountDueinCapital(BigDecimal.ZERO);
		this.setAmountDueinInterest(BigDecimal.ZERO);
		this.setAmountDueinRow(0);
		this.setAmountDueRepayAll(BigDecimal.ZERO);
		this.setAmountDueRepayCapital(BigDecimal.ZERO);
		this.setAmountDueRepayedAll(BigDecimal.ZERO);
		this.setAmountDueRepayCapital(BigDecimal.ZERO);
		this.setAmountDueRepayedInterest(BigDecimal.ZERO);
		this.setAmountDueRepayedRow(0);
		this.setAmountDueRepayInterest(BigDecimal.ZERO);
		this.setAmountDueRepayRow(0);
		this.setAmountInedAll(BigDecimal.ZERO);
		this.setAmountInedCapital(BigDecimal.ZERO);
		this.setAmountInedInterest(BigDecimal.ZERO);
		this.setAmountInedRow(0);
		this.setAmountInterestFee(BigDecimal.ZERO);
		this.setAmountInvest(BigDecimal.ZERO);
		this.setAmountInvestRow(0);
		this.setAmountInvit(BigDecimal.ZERO);
		this.setAmountRecharge(BigDecimal.ZERO);
		this.setAmountRechargeRow(0);
		this.setCreateBy("webAPISystem");
		this.setCreateTime(new Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BigDecimal getAmountAward() {
		return amountAward;
	}

	public void setAmountAward(BigDecimal amountAward) {
		this.amountAward = amountAward;
	}

	public BigDecimal getAmountAwardInvest() {
		return amountAwardInvest;
	}

	public void setAmountAwardInvest(BigDecimal amountAwardInvest) {
		this.amountAwardInvest = amountAwardInvest;
	}

	public Integer getAmountAwardRow() {
		return amountAwardRow;
	}

	public void setAmountAwardRow(Integer amountAwardRow) {
		this.amountAwardRow = amountAwardRow;
	}

	public BigDecimal getAmountCash() {
		return amountCash;
	}

	public void setAmountCash(BigDecimal amountCash) {
		this.amountCash = amountCash;
	}

	public Integer getAmountCashRow() {
		return amountCashRow;
	}

	public void setAmountCashRow(Integer amountCashRow) {
		this.amountCashRow = amountCashRow;
	}

	public BigDecimal getAmountDueRepayAll() {
		return amountDueRepayAll;
	}

	public void setAmountDueRepayAll(BigDecimal amountDueRepayAll) {
		this.amountDueRepayAll = amountDueRepayAll;
	}

	public BigDecimal getAmountDueRepayCapital() {
		return amountDueRepayCapital;
	}

	public void setAmountDueRepayCapital(BigDecimal amountDueRepayCapital) {
		this.amountDueRepayCapital = amountDueRepayCapital;
	}

	public BigDecimal getAmountDueRepayInterest() {
		return amountDueRepayInterest;
	}

	public void setAmountDueRepayInterest(BigDecimal amountDueRepayInterest) {
		this.amountDueRepayInterest = amountDueRepayInterest;
	}

	public Integer getAmountDueRepayRow() {
		return amountDueRepayRow;
	}

	public void setAmountDueRepayRow(Integer amountDueRepayRow) {
		this.amountDueRepayRow = amountDueRepayRow;
	}

	public BigDecimal getAmountDueRepayedAll() {
		return amountDueRepayedAll;
	}

	public void setAmountDueRepayedAll(BigDecimal amountDueRepayedAll) {
		this.amountDueRepayedAll = amountDueRepayedAll;
	}

	public BigDecimal getAmountDueRepayedCapital() {
		return amountDueRepayedCapital;
	}

	public void setAmountDueRepayedCapital(BigDecimal amountDueRepayedCapital) {
		this.amountDueRepayedCapital = amountDueRepayedCapital;
	}

	public BigDecimal getAmountDueRepayedInterest() {
		return amountDueRepayedInterest;
	}

	public void setAmountDueRepayedInterest(BigDecimal amountDueRepayedInterest) {
		this.amountDueRepayedInterest = amountDueRepayedInterest;
	}

	public Integer getAmountDueRepayedRow() {
		return amountDueRepayedRow;
	}

	public void setAmountDueRepayedRow(Integer amountDueRepayedRow) {
		this.amountDueRepayedRow = amountDueRepayedRow;
	}

	public BigDecimal getAmountDueinAll() {
		return amountDueinAll;
	}

	public void setAmountDueinAll(BigDecimal amountDueinAll) {
		this.amountDueinAll = amountDueinAll;
	}

	public BigDecimal getAmountDueinCapital() {
		return amountDueinCapital;
	}

	public void setAmountDueinCapital(BigDecimal amountDueinCapital) {
		this.amountDueinCapital = amountDueinCapital;
	}

	public BigDecimal getAmountDueinInterest() {
		return amountDueinInterest;
	}

	public void setAmountDueinInterest(BigDecimal amountDueinInterest) {
		this.amountDueinInterest = amountDueinInterest;
	}

	public Integer getAmountDueinRow() {
		return amountDueinRow;
	}

	public void setAmountDueinRow(Integer amountDueinRow) {
		this.amountDueinRow = amountDueinRow;
	}

	public BigDecimal getAmountInedAll() {
		return amountInedAll;
	}

	public void setAmountInedAll(BigDecimal amountInedAll) {
		this.amountInedAll = amountInedAll;
	}

	public BigDecimal getAmountInedCapital() {
		return amountInedCapital;
	}

	public void setAmountInedCapital(BigDecimal amountInedCapital) {
		this.amountInedCapital = amountInedCapital;
	}

	public BigDecimal getAmountInedInterest() {
		return amountInedInterest;
	}

	public void setAmountInedInterest(BigDecimal amountInedInterest) {
		this.amountInedInterest = amountInedInterest;
	}

	public Integer getAmountInedRow() {
		return amountInedRow;
	}

	public void setAmountInedRow(Integer amountInedRow) {
		this.amountInedRow = amountInedRow;
	}

	public BigDecimal getAmountInvest() {
		return amountInvest;
	}

	public void setAmountInvest(BigDecimal amountInvest) {
		this.amountInvest = amountInvest;
	}

	public Integer getAmountInvestRow() {
		return amountInvestRow;
	}

	public void setAmountInvestRow(Integer amountInvestRow) {
		this.amountInvestRow = amountInvestRow;
	}

	public BigDecimal getAmountRecharge() {
		return amountRecharge;
	}

	public void setAmountRecharge(BigDecimal amountRecharge) {
		this.amountRecharge = amountRecharge;
	}

	public Integer getAmountRechargeRow() {
		return amountRechargeRow;
	}

	public void setAmountRechargeRow(Integer amountRechargeRow) {
		this.amountRechargeRow = amountRechargeRow;
	}

	public BigDecimal getAmountInvit() {
		return amountInvit;
	}

	public void setAmountInvit(BigDecimal amountInvit) {
		this.amountInvit = amountInvit;
	}

	public BigDecimal getAmountInterestFee() {
		return amountInterestFee;
	}

	public void setAmountInterestFee(BigDecimal amountInterestFee) {
		this.amountInterestFee = amountInterestFee;
	}

	public BigDecimal getInvestIntegral() {
		return investIntegral;
	}

	public void setInvestIntegral(BigDecimal investIntegral) {
		this.investIntegral = investIntegral;
	}

}
