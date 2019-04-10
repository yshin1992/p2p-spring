package org.domain.member;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 企业联系人实体
 *
 */
@Entity
@Table(name="companycontact")
public class CompanyContact extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    @Column(length=32)
    private String contactId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="companyId")
    private Enterprise enterprise;
    /**
     * 联系人姓名
     */
    @Column(length=64)
    private String contactNm;

    /**
     * 联系人职务
     */
    @Column(length=64)
    private String contactDuty;

    /**
     * 联系人类别
     */
    @Column
    private Integer contactKind;

    /**
     * 手机号码
     */
    @Column(length=32)
    private String contactPhone;

    /**
     * 电子邮箱
     */
    @Column(length=128)
    private String email;

    /**
     * 办公电话
     */
    @Column(length=32)
    private String officePhone;

    /**
     * 传真号码
     */
    @Column(length=32)
    private String contactFax;

    /**
     * 备注
     */
    @Column(length=255)
    private String contactDesc;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getContactNm() {
        return contactNm;
    }

    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }

    public String getContactDuty() {
        return contactDuty;
    }

    public void setContactDuty(String contactDuty) {
        this.contactDuty = contactDuty;
    }

    public Integer getContactKind() {
        return contactKind;
    }

    public void setContactKind(Integer contactKind) {
        this.contactKind = contactKind;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getContactDesc() {
        return contactDesc;
    }

    public void setContactDesc(String contactDesc) {
        this.contactDesc = contactDesc;
    }

    @Override
    public String getId() {
        return getContactId();
    }

    @Override
    public void setId(String id) {
        this.contactId = id;
    }
}
