package org.domain.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;
import org.util.DecimalUtil;

@Entity
@Table
public class ItemType extends AbstractEntity {

private static final long serialVersionUID = 4185505904034918759L;
	
	public static final String ITEM_TYPE_CODE_CAPITAL = "100000";// 本金
	public static final String ITEM_TYPE_CODE_INVEST = "100100";// 投资
	public static final String ITEM_TYPE_CODE_TRANSFER = "100200";// 债权转让
	public static final String ITEM_TYPE_CODE_RECHARGE = "100300";// 充值
	public static final String ITEM_TYPE_CODE_WITHDRAWALS = "100400";// 提现 
	public static final String ITEM_TYPE_CODE_LOAN = "100500";// 借款（放款）
	public static final String ITEM_TYPE_CODE_REPAY = "100600";// 还款
	public static final String ITEM_TYPE_CODE_RECEIVABLE = "100700";// 收款
	public static final String ITEM_TYPE_CODE_INTEREST = "100800";// 利息
	public static final String ITEM_TYPE_CODE_OVERDUE_INTEREST = "200000";// 逾期利息
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String itemTypeId;
	/**
	 * 费用项目编码
	 */
	@Column(length = 6, unique = true)
	private String itemTypeCd;
	/**
	 * 费用项目名称
	 */
	@Column(length = 64)
	private String itemTypeNm;
	/**
	 * 付款方
	 */
	@Column
	private Integer biller;
	/**
	 * 收款方
	 */
	@Column
	private Integer charger;
	/**
	 * 费用节点
	 */
	@Column
	private Integer node;
	/**
	 * 是否可编辑
	 */
	@Column
	private Integer edited;
	/**
	 * 费率参考
	 */
	@Column
	private Integer rateReferened;
	/**
	 * 付费费率
	 */
	@Column(precision = 19, scale = 4)
	private BigDecimal rate;
	/**
	 * 期数或天数
	 */
	@Column
	private Integer periodOrDay;
	/**
	 * 下限
	 */
	@Column(precision = 19, scale = 4)
	private BigDecimal minAmount;
	/**
	 * 上限
	 */
	@Column(precision = 19, scale = 4)
	private BigDecimal maxAmount;
	/**
	 * 上级分类编码
	 */
	@Column(length = 6)
	private String itemTypePCd;
	/**
	 * 费用分类
	 */
	@Column(length = 3)
	private String feeType;
	
	/**
	 * 是否参与线上运算标识位（0否、1是）
	 */
	@Column
	private Integer calOnlineFlag;
	
	/**
	 * 分润模板名称
	 */
	@Transient
	private String templateNm;

	public String getTemplateNm() {
		return templateNm;
	}

	public void setTemplateNm(String templateNm) {
		this.templateNm = templateNm;
	}

	@Override
	public String getId() {
		return getItemTypeId();
	}

	@Override
	public void setId(String id) {
		setItemTypeId(id);
	}

	public String getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(String itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getItemTypeCd() {
		return itemTypeCd;
	}

	public void setItemTypeCd(String itemTypeCd) {
		this.itemTypeCd = itemTypeCd;
	}

	public String getItemTypeNm() {
		return itemTypeNm;
	}

	public void setItemTypeNm(String itemTypeNm) {
		this.itemTypeNm = itemTypeNm;
	}

	public Integer getBiller() {
		return biller;
	}

	public String getBillerName() {
		if (!StringUtils.isEmpty(biller)) {
			switch (biller) {
			case 1:
				return "融资人";
			case 2:
				return "投资人";
			case 3:
				return "转让人";
			case 4:
				return "受让人";
			case 11:
				return "平台";
			case 12:
				return "担保方";
			case 13:
				return "三方支付公司";
			case 15:
				return "渠道商";
			default:
				return "";
			}
		}
		return "";
	}

	public String getChargerName() {
		if (!StringUtils.isEmpty(charger)) {
			switch (charger) {
			case 1:
				return "融资人";
			case 2:
				return "投资人";
			case 3:
				return "转让人";
			case 4:
				return "受让人";
			case 11:
				return "平台";
			case 12:
				return "担保方";
			case 13:
				return "三方支付公司";
			case 15:
				return "渠道商";
			default:
				return "";
			}
		}
		return "";
	}

	public String getNodeName() {
		if (!StringUtils.isEmpty(node)) {
			switch (node) {
			case 1:
				return "借款成功";
			case 2:
				return "正常还款";
			case 3:
				return "提前还款";
			case 4:
				return "逾期还款";
			case 5:
				return "债权转让";
			case 6:
				return "追偿还款";
			default:
				return "";
			}
		}
		return "";
	}

	public void setBiller(Integer biller) {
		this.biller = biller;
	}

	public Integer getCharger() {
		return charger;
	}

	public void setCharger(Integer charger) {
		this.charger = charger;
	}

	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public Integer getEdited() {
		return edited;
	}

	public void setEdited(Integer edited) {
		this.edited = edited;
	}

	public String getRateReferenedNm() {
		if(!StringUtils.isEmpty(rateReferened)){
			switch (rateReferened) {
			case 0:
				return "只作为费用类型被引用(不计算)";
			case 1:
				return "全部本金";
			case 2:
				return "全部利息";
			case 3:
				return "全部本息";
			case 4:
				return "当期本金";
			case 5:
				return "当期利息";
			case 6:
				return "当期本息";
			case 7:
				return "剩余本金(包含本期)";
			case 8:
				return "剩余本金(不含本期)";
			case 9:
				return "转让成交额";
			case 11:
				return "充值金额";
			case 12:
				return "提现金额";
			default:
				return "";
			}
		}
		return "";
	}

	public Integer getRateReferened() {
		return rateReferened;
	}

	public void setRateReferened(Integer rateReferened) {
		this.rateReferened = rateReferened;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * 参考费率
	 * 
	 * @return
	 */
	@Transient
	public BigDecimal getRateP() {
		return DecimalUtil.toPercent(rate);
	}

	@Transient
	public BigDecimal getRateD() {
		return DecimalUtil.fromPercent(rate);
	}

	public Integer getPeriodOrDay() {
		return periodOrDay;
	}

	public void setPeriodOrDay(Integer periodOrDay) {
		this.periodOrDay = periodOrDay;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getItemTypePCd() {
		return itemTypePCd;
	}

	public void setItemTypePCd(String itemTypePCd) {
		this.itemTypePCd = itemTypePCd;
	}

	public String getRateDisp() {
		if (rate != null) {
			return rate.multiply(new BigDecimal(100)).setScale(2).toString();
		} else {
			return "";
		}
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public Integer getCalOnlineFlag() {
		return calOnlineFlag;
	}

	public void setCalOnlineFlag(Integer calOnlineFlag) {
		this.calOnlineFlag = calOnlineFlag;
	}
}
