package org.domain.product;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 产品质押物拍卖信息
 */
@Entity
@Table(name = "product_pledge")
public class ProductPledge extends AbstractEntity {
    private static final long serialVersionUID = -5013516657784981705L;
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(length = 32)
    private String id;

    /**
     * 作者
     */
    @Column(length = 32)
    private String author;
    /**
     * 作品名称
     */
    @Column(length = 50)
    private String workName;
    /**
     * 作品尺寸
     */
    @Column(length = 50)
    private String size;
    /**
     * 拍卖公司
     */
    @Column(length = 100)
    private String auctionCompany;
    /**
     * 拍卖时间
     */
    @Column(length = 50)
    private String auctionTime;

    /**
     * 成交价格
     */
    @Column(precision = 19, scale = 4)
    private BigDecimal dealAmt;
    /**
     * 纪录类型
     * 0-当前拍卖纪录
     * 1-相关拍卖纪录类型
     */
    @Column(length = 1)
    private Integer pledgeType;
    /**
     * 备注
     */
    @Column(length = 2000)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAuctionCompany() {
        return auctionCompany;
    }

    public void setAuctionCompany(String auctionCompany) {
        this.auctionCompany = auctionCompany;
    }

    public String getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(String auctionTime) {
        this.auctionTime = auctionTime;
    }

    public BigDecimal getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(BigDecimal dealAmt) {
        this.dealAmt = dealAmt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPledgeType() {
        return pledgeType;
    }

    public void setPledgeType(Integer pledgeType) {
        this.pledgeType = pledgeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}