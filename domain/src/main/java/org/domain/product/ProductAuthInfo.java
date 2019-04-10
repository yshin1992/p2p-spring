package org.domain.product;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="product_authinfo")
public class ProductAuthInfo extends AbstractEntity
{
    private static final long serialVersionUID = -1326489022196959136L;

    @Id
    @GenericGenerator(name="systemUUID", strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    @Column(length=32)
    private String id;

    @Column(length=1)
    private Integer infoType;

    @Column(length=50)
    private String auditProject;

    @Column(length=50)
    private String auditProjectNm;

    @Column(length=1)
    private Integer status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date passTime;

    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;

    public Product getProduct()
    {
        return this.product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getInfoType() {
        return this.infoType;
    }
    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }
    public String getAuditProject() {
        return this.auditProject;
    }
    public void setAuditProject(String auditProject) {
        this.auditProject = auditProject;
    }
    public String getAuditProjectNm() {
        return this.auditProjectNm;
    }
    public void setAuditProjectNm(String auditProjectNm) {
        this.auditProjectNm = auditProjectNm;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getPassTime() {
        return this.passTime;
    }
    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
