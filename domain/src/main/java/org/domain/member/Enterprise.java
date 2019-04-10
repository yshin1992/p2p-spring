package org.domain.member;

import org.domain.AbstractEntity;
import org.domain.product.Attachment;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="company")
public class Enterprise extends AbstractEntity {

    @Id
    @GenericGenerator(name = "generator",
            strategy = "foreign",
            parameters = {
                    @org.hibernate.annotations.Parameter( name = "property", value = "member" )
            })
    @GeneratedValue(generator = "generator")
    @Column(length=32)
    private String memberId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "enterprise")
    private Member member;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<CompanyContact> companyContacts;

    /**
     * 企业名称
     */
    @Column(length=255)
    private String companyNm;

    /**
     * 组织机构编码
     */
    @Column(length=16)
    private String companyCd;

    /**
     * 营业执照号
     */
    @Column(length=32)
    private String companyLicence;

    /**
     * 税务登记号
     */
    @Column(length=32)
    private String taxNo;

    /**
     * 法人代表
     */
    @Column(length=64)
    private String legalPerson;
    /**
     * 法人身份证号
     */
    @Column(length=18)
    private String legalPersonIDCard;
    /**
     * 成立时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date foundTime;

    /**
     * 注册资金
     */
    @Column
    private BigDecimal registedFund;

    /**
     * 经营地址
     */
    @Column(length=256)
    private String companyAddress;

    /**
     * 经营范围
     */
    @Column(length=2000)
    private String companyRange;

    /**
     * 经营状况
     */
    @Column(length=2000)
    private String companyOperate;

    /**
     * 财务状况
     */
    @Column(length=2000)
    private String companyFinance;

    /**
     * 企业介绍
     */
    @Column(length=2000)
    private String companyDesc;

    /**
     * 企业涉诉状况
     */
    @Column(length=2000)
    private String companyComplaint;

    /**
     * 企业信用状况
     */
    @Column(length=2000)
    private String companyCredit;

    /**
     * 审核状态:0,申请.1、确认.2.失败
     */
    @Column
    private Integer status;

    @Column(length=32)
    private String updateBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date upateTime;

    /**
     * 股东信息
     */
    @Column(length=2048)
    private String shareholderInfo;

    /**
     *	公司电话
     */
    private String companyPhone;

    /**
     * 企业相关附件信息
     */
    @OneToMany(cascade={CascadeType.ALL})
    @JoinTable(joinColumns={@JoinColumn(name="memberId")}
            ,inverseJoinColumns={@JoinColumn(name="attachmentId")})
    private List<Attachment> attachments;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<CompanyContact> getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(Set<CompanyContact> companyContacts) {
        this.companyContacts = companyContacts;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getCompanyLicence() {
        return companyLicence;
    }

    public void setCompanyLicence(String companyLicence) {
        this.companyLicence = companyLicence;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonIDCard() {
        return legalPersonIDCard;
    }

    public void setLegalPersonIDCard(String legalPersonIDCard) {
        this.legalPersonIDCard = legalPersonIDCard;
    }

    public Date getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    public BigDecimal getRegistedFund() {
        return registedFund;
    }

    public void setRegistedFund(BigDecimal registedFund) {
        this.registedFund = registedFund;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyRange() {
        return companyRange;
    }

    public void setCompanyRange(String companyRange) {
        this.companyRange = companyRange;
    }

    public String getCompanyOperate() {
        return companyOperate;
    }

    public void setCompanyOperate(String companyOperate) {
        this.companyOperate = companyOperate;
    }

    public String getCompanyFinance() {
        return companyFinance;
    }

    public void setCompanyFinance(String companyFinance) {
        this.companyFinance = companyFinance;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyComplaint() {
        return companyComplaint;
    }

    public void setCompanyComplaint(String companyComplaint) {
        this.companyComplaint = companyComplaint;
    }

    public String getCompanyCredit() {
        return companyCredit;
    }

    public void setCompanyCredit(String companyCredit) {
        this.companyCredit = companyCredit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }

    public String getShareholderInfo() {
        return shareholderInfo;
    }

    public void setShareholderInfo(String shareholderInfo) {
        this.shareholderInfo = shareholderInfo;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getUpdateTimeStr() {
        if(null==getUpateTime())
            return "";
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(getUpateTime());
    }

    public String getCreateTimeStr() {
        if(null==getCreateTime())
            return "";
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(getCreateTime());
    }

    public String getFoundTimeStr() {
        if(null==getFoundTime())
            return "";
        else
            return new SimpleDateFormat("yyyy-MM-dd").format(getFoundTime());
    }

    @Override
    public void setId(String id) {
        this.memberId = id;
    }

    @Override
    public String getId() {
        return this.memberId;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
