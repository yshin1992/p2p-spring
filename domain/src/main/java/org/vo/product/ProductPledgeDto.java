package org.vo.product;

import java.math.BigDecimal;

public class ProductPledgeDto
{
	  private String id;
	  private String author;
	  private String workName;
	  private String width;
	  private String height;
	  private String auctionCompany;
	  private String auctionTime;
	  private BigDecimal dealAmt;
	  private Integer pledgeType;
	  private String remark;

	  public String getAuthor()
	  {
	    return this.author;
	  }
	  public void setAuthor(String author) {
	    this.author = author;
	  }
	  public String getWorkName() {
	    return this.workName;
	  }
	  public void setWorkName(String workName) {
	    this.workName = workName;
	  }
	  public String getWidth() {
	    return this.width;
	  }
	  public void setWidth(String width) {
	    this.width = width;
	  }
	  public String getHeight() {
	    return this.height;
	  }
	  public void setHeight(String height) {
	    this.height = height;
	  }
	  public String getAuctionCompany() {
	    return this.auctionCompany;
	  }
	  public void setAuctionCompany(String auctionCompany) {
	    this.auctionCompany = auctionCompany;
	  }
	  public String getAuctionTime() {
	    return this.auctionTime;
	  }
	  public void setAuctionTime(String auctionTime) {
	    this.auctionTime = auctionTime;
	  }
	  public BigDecimal getDealAmt() {
	    return this.dealAmt;
	  }
	  public void setDealAmt(BigDecimal dealAmt) {
	    this.dealAmt = dealAmt;
	  }
	  public Integer getPledgeType() {
	    return this.pledgeType;
	  }
	  public void setPledgeType(Integer pledgeType) {
	    this.pledgeType = pledgeType;
	  }
	  public String getRemark() {
	    return this.remark;
	  }
	  public void setRemark(String remark) {
	    this.remark = remark;
	  }
	  public String getId() {
	    return this.id;
	  }
	  public void setId(String id) {
	    this.id = id;
	  }
	}
