package org.domain.product;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.util.DecimalUtil;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="product_itemtype")
public class ProductItemType extends AbstractEntity {

    private static final long serialVersionUID = 7165032785803786109L;

    /**
     * 借款成功
     */
    public static final Integer NODE_LOAN = 1;
    /**
     * 正常还款
     */
    public static final Integer NODE_REPAYMENT = 2;
    /**
     * 提前还款
     */
    public static final Integer NODE_EARLIER_REPAYMENT = 3;
    /**
     * 逾期还款
     */
    public static final Integer NODE_OVERDUE_REPAYMENT = 4;
    /**
     * 债权转让
     */
    public static final Integer NODE_CREDIT_TRANSFER = 5;
    /**
     * 追偿还款
     */
    public static final Integer NODE_CLAIM = 6;

    /**
     * 收款方、付款方角色：融资人
     */
    public static final int ROLE_BORROWER = 1;

    /**
     * 收款方、付款方角色：投资人
     */
    public static final int ROLE_INVESTOR = 2;

    /**
     * 收款方、付款方角色：转让人
     */
    public static final int ROLE_OUT_TRANSFEROR = 3;

    /**
     * 收款方、付款方角色：受让人
     */
    public static final int ROLE_IN_TRANSFEREE = 4;

    /**
     * 收款方、付款方角色：平台
     */
    public static final int ROLE_PLATFORM = 11;

    /**
     * 收款方、付款方角色：担保方
     */
    public static final int ROLE_GUARANTOR = 12;

    /**
     * 收款方、付款方角色：三方支付公司
     */
    public static final int ROLE_PAY_PROVIDER = 13;

    /**
     * 收款方、付款方角色：网站/系统/软件开发方
     */
    public static final int ROLE_DEVELOPER = 14;

    /**
     * 收款方、付款方角色：渠道商
     */
    public static final int ROLE_CHANNEL = 15;

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(length = 32)
    private String productItemTypeId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product Product;
    /**
     * 费用标识
     */
    @Column(length = 32)
    private String itemTypeId;
    /**
     * 费用项目编码
     */
    @Column(length = 6)
    private String itemTypeCd;
    /**
     * 费用项目名称
     */
    @Column(length = 64)
    private String itemTypeNm;
    /**
     * 模板Id(冗余)
     */
    @Column(length = 32)
    private String templateId;
    /**
     * 模板名称(冗余)
     */
    @Column(length = 128)
    private String templateNm;
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
    private Integer periodOrDay =0;
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

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {
    }

    public String getProductItemTypeId() {
        return productItemTypeId;
    }

    public void setProductItemTypeId(String productItemTypeId) {
        this.productItemTypeId = productItemTypeId;
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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateNm() {
        return templateNm;
    }

    public void setTemplateNm(String templateNm) {
        this.templateNm = templateNm;
    }

    public Integer getBiller() {
        return biller;
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
        if (ObjectUtils.isEmpty(rateReferened)) {
            return "";
        }
        switch (rateReferened) {
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
    public Integer getPeriodOrDay() {
        return periodOrDay;
    }

    public void setPeriodOrDay(Integer periodOrDay) {
        this.periodOrDay = periodOrDay;
    }

    public BigDecimal getMinAmount() {
        //if(StringUtil.isEmpty(minAmount)){
        //	return BigDecimal.ZERO;
        //}else{
        return minAmount;
        //}

    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }
    public BigDecimal getMaxAmount() {

        //if(StringUtil.isEmpty(maxAmount)){
        //	return BigDecimal.ZERO;
        //}else{
        return maxAmount;
        //}

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

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    @Transient
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

    @Transient
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

    @Transient
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

    @Transient
    public String getRateReferenedName() {
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

    @Override
    public String toString() {
        return "ProductItemType [productItemTypeId=" + productItemTypeId
                + ", Product=" + Product + ", itemTypeId=" + itemTypeId
                + ", itemTypeCd=" + itemTypeCd + ", itemTypeNm=" + itemTypeNm
                + ", templateId=" + templateId + ", templateNm=" + templateNm
                + ", biller=" + biller + ", charger=" + charger + ", node="
                + node + ", edited=" + edited + ", rateReferened="
                + rateReferened + ", rate=" + rate + ", periodOrDay="
                + periodOrDay + ", minAmount=" + minAmount + ", maxAmount="
                + maxAmount + ", itemTypePCd=" + itemTypePCd + "]";
    }

    public BigDecimal calcFee(BigDecimal feeReference) {
        BigDecimal fee = BigDecimal.ZERO;
        if (rate != null) {
            if (feeReference != null) {
                fee = feeReference.multiply(rate);
            }
        }
        if (fee.compareTo(minAmount) == -1) {
            fee = minAmount;
        } else if (fee.compareTo(maxAmount) == 1) {
            fee = maxAmount;
        }
        return fee;
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