package org.domain.member;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 汇付天下账户信息
 * @author yanshuai
 *
 */
@Entity
public class Account extends AbstractEntity {

	private static final long serialVersionUID = 5912441347449663127L;

	public static final Integer AUTHORIZEED = 1;
	public static final Integer UNAUTHORIZE = 0;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String accountId;// 账户标识

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "memberId")
	private Member member;

	@Column(length = 64)
	private String accountCd;// 三方账号

	@Column(length = 64)
	private String accountNm;// 账号名

	@Column(precision = 19, scale = 4)
	private BigDecimal amountFronze;// 冻结资金

	@Column(precision = 19, scale = 4)
	private BigDecimal amountTotal;// 总金额

	@Column(precision = 19, scale = 4)
	private BigDecimal amountVerify;// 有效金额

	@Column()
	private Integer authorize;// 授权情况
	
	@Column(length = 32)
	private String trxId;//交易流水号
	@Column(length = 10)
	private String statusCode;//状态代码
	@Column(length = 32)
	private String statusDesc;//状态描述

	/**
	 * 是否默认账户
	 */
	@Column
	private Boolean defaultAccount;

	/**
	 * 银行用支付密码
	 */
	@Column
	private String payPwd;


	/**
	 * 银行用支付密码
	 * @return
	 */
	public String getPayPwd() {
		return payPwd;
	}

	/**
	 * 银行用支付密码
	 * @param payPwd
	 */
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	/**
	 * 是否默认账户
	 * @return
	 */
	public Boolean getDefaultAccount() {
		return defaultAccount;
	}

	/**
	 * 是否默认账户
	 * @param defaultAccount
	 */
	public void setDefaultAccount(Boolean defaultAccount) {
		this.defaultAccount = defaultAccount;
	}

	/**
	 * 账户标识
	 * @param accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * 账户标识
	 * @return
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * 关联会员
	 * @return
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 关联会员
	 * @param member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * 三方账号
	 * 
	 * @return
	 */
	public String getAccountCd() {
		return accountCd;
	}

	/**
	 * 三方账号
	 * 
	 * @param accountCd
	 */
	public void setAccountCd(String accountCd) {
		this.accountCd = accountCd;
	}

	/**
	 * 账号名
	 * 
	 * @return
	 */
	public String getAccountNm() {
		return accountNm;
	}

	/**
	 * 账号名
	 * 
	 * @param accountNm
	 */
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}

	/**
	 * 冻结资金
	 * 
	 * @return
	 */
	public BigDecimal getAmountFronze() {
		return amountFronze;
	}

	/**
	 * 冻结资金
	 * 
	 * @param amountFronze
	 */
	public void setAmountFronze(BigDecimal amountFronze) {
		this.amountFronze = amountFronze;
	}

	/**
	 * 总金额
	 * 
	 * @return
	 */
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	/**
	 * 总金额
	 * 
	 * @param amountTotal
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	/**
	 * 有效金额
	 * @return
	 */
	public BigDecimal getAmountVerify() {
		return amountVerify;
	}

	/**
	 * 有效金额
	 * @param amountVerify
	 */
	public void setAmountVerify(BigDecimal amountVerify) {
		this.amountVerify = amountVerify;
	}

	/**
	 * 授权情况
	 * @return
	 */
	public Integer getAuthorize() {
		return authorize;
	}

	/**
	 * 授权情况
	 * @param authorize
	 */
	public void setAuthorize(Integer authorize) {
		this.authorize = authorize;
	}

	@Override
	public String getId() {
		return this.getAccountId();
	}

	@Override
	public void setId(String id) {
		this.setAccountId(id);

	}

	/**
	 * 交易流水号
	 * @return
	 */
	public String getTrxId() {
		return trxId;
	}
	/**
	 * 交易流水号
	 * @param trxId
	 */

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}
	/**
	 * 状态代码
	 * @return
	 */

	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * 状态代码
	 * @param statusCode
	 */

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * 状态描述
	 * @return
	 */

	public String getStatusDesc() {
		return statusDesc;
	}
	/**
	 * 状态描述
	 * @param statusDesc
	 */

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

}
