package org.domain.product;

import org.domain.AbstractEntity;
import org.domain.member.Enterprise;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 担保措施
 */
@Entity
@Table
public class Safeguard extends AbstractEntity {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column
    private String safeguardId;

    @OneToOne
    @JoinColumn(name="productId")
    private Product product;


    /**
     * 对应企业
     */
    @ManyToOne(optional=true)
    @JoinColumn(name="companyId")
    private Enterprise enterprise;

    /**
     * 担保函编号
     */
    @Column(length=64)
    private String backletterCd;
    /**
     * 担保意见
     */
    @Column(length=2000)
    private String guaranteeDesc;

    /**
     * 保障措施说明
     */
    @Column(length=2000)
    private String safeguardDesc;
    /**
     * 抵押物详情
     */
    @Column(length=2000)
    private String pawnDesc;
    /**
     * 借款人详情(企业涉诉状况)
     */
    @Column(length=2000)
    private String debtDesc;

    /**
     * 风控详细(企业征信记录)
     */
    @Column(length=2000)
    private String riskDesc;

    public String getSafeguardId() {
        return safeguardId;
    }

    public void setSafeguardId(String safeguardId) {
        this.safeguardId = safeguardId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getBackletterCd() {
        return backletterCd;
    }

    public void setBackletterCd(String backletterCd) {
        this.backletterCd = backletterCd;
    }

    public String getGuaranteeDesc() {
        return guaranteeDesc;
    }

    public void setGuaranteeDesc(String guaranteeDesc) {
        this.guaranteeDesc = guaranteeDesc;
    }

    public String getSafeguardDesc() {
        return safeguardDesc;
    }

    public void setSafeguardDesc(String safeguardDesc) {
        this.safeguardDesc = safeguardDesc;
    }

    public String getPawnDesc() {
        return pawnDesc;
    }

    public void setPawnDesc(String pawnDesc) {
        this.pawnDesc = pawnDesc;
    }

    public String getDebtDesc() {
        return debtDesc;
    }

    public void setDebtDesc(String debtDesc) {
        this.debtDesc = debtDesc;
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    @Override
    public void setId(String id) {
        this.safeguardId = id;
    }

    @Override
    public String getId() {
        return this.safeguardId;
    }
}
